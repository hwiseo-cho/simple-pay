package com.hwiseo.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwiseo.consumer.entity.Member;
import com.hwiseo.consumer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final MemberRepository memberRepository;

    /**
     *  ConsumerRecords를 받음
     */
    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "member", partitions = {"0"}),
            @TopicPartition(topic = "account", partitions = {"1"})}, groupId = "test-group-00")
    public void batchListener(ConsumerRecords<String, String> records) {
        records.forEach(record -> log.info(record.toString()));
    }

}
