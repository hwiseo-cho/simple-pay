package com.hwiseo.consumer.config;

import com.hwiseo.consumer.service.ConsumerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

/**
 * 1개의 애플리케이션 n개의 컨슈머 스레드
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ConsumerWorker implements Runnable{

    @Autowired
    private ConsumerService consumerService;

    private Properties props;
    private ArrayList<String> topics = new ArrayList<>();
    private String threadName;
    private KafkaConsumer<String, String> consumer;

    private static Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

    public ConsumerWorker(Properties props, ArrayList<String> topics, int number) {
        this.props = props;
        this.topics = topics;
        this.threadName = "consumer-thread-" + number;
    }

    @Override
    public void run() {
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(topics);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                currentOffset = new HashMap<>();

                for (ConsumerRecord<String, String> record : records) {
                    log.info("consumer result: {} {}",record.key(), record.value());
                    currentOffset.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, null));

                    // 받아온 레코드 DB 저장
                    consumerService.sendToAny(record);

                    consumer.commitSync(currentOffset);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            consumer.close();
        }
    }
}
