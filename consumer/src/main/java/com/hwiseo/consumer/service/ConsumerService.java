package com.hwiseo.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwiseo.consumer.common.Topic;
import com.hwiseo.consumer.entity.Member;
import com.hwiseo.consumer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsumerService {

    private final MemberRepository memberRepository;

    private final ApplicationContext applicationContext;


    /**
     *  ConsumerRecords를 받음
     */
    @KafkaListener(topics = {"member" , "account"}, containerFactory = "customContainerFactory", groupId = "all-group")
    public void recordListener(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic, String record) throws JsonProcessingException {
        log.info(record);
        if(Topic.MEMBER.getTopicName().equals(topic)) {
            Member member = new ObjectMapper().readValue(record, Member.class);
            memberRepository.createMember(member);
        } else {

        }

    }

}
