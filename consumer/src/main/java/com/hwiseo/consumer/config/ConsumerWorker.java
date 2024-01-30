package com.hwiseo.consumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 1개의 애플리케이션 n개의 컨슈머 스레드
 */
@Slf4j
@RequiredArgsConstructor
public class ConsumerWorker implements Runnable{

    private Properties props;
    private String topic;
    private String threadName;
    private KafkaConsumer<String, String> consumer;

    private static Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

    public ConsumerWorker(Properties props, String topic, int number) {
        this.props = props;
        this.topic = topic;
        this.threadName = "consumer-thread-" + number;
    }

    @Override
    public void run() {
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                currentOffset = new HashMap<>();

                for (ConsumerRecord<String, String> record : records) {
                    log.info("consumer result: {} {}",record.key(), record.value());
                    currentOffset.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, null));
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
