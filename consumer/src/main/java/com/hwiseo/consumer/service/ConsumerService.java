package com.hwiseo.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwiseo.consumer.common.Topic;
import com.hwiseo.consumer.entity.Member;
import com.hwiseo.consumer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsumerService {

    private final MemberRepository memberRepository;

    /**
     *  ConsumerRecords를 받음
     */
    @KafkaListener(topics = {"member" , "account"}, containerFactory = "customContainerFactory", groupId = "all-group")
    public void recordListener(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic, ConsumerRecord<String, String> record) throws JsonProcessingException {

        if(Topic.MEMBER.getTopicName().equals(topic)) {
            processMember(record);
        } else if(Topic.ACCOUNT.getTopicName().equals(topic)){
            processAccount(record);
        }

    }

    private void processMember(ConsumerRecord<String, String> record)  {
        try {
            String key = record.key();
            Member member = new ObjectMapper().readValue(record.value(), Member.class);
            ReflectionUtils.invokeMethod(MemberRepository.class.getMethod(key, Member.class), memberRepository, member);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void processAccount(ConsumerRecord<String, String> record)  {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
