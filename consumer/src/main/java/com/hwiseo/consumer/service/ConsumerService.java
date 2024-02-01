package com.hwiseo.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwiseo.consumer.entity.Member;
import com.hwiseo.consumer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final MemberRepository memberRepository;

    public void sendToAny(ConsumerRecord<String, String> record) {
        try {
            if("member".equals(record.topic())) {
                log.info("sendToAny param {}", record.toString());
                memberRepository.createMember(new ObjectMapper().readValue(record.value(), Member.class));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
