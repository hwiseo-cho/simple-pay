package com.hwiseo.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwiseo.app.common.Topic;
import com.hwiseo.app.common.Topic;
import com.hwiseo.app.domain.Account;
import com.hwiseo.app.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public String sendKafka(Topic topic, String key, Object object) {
        final String[] resultCod = new String[1];

        try {
            // 받아온 토픽으로 설정
            String topicName = topic.getTopicName();

            // 받아온 값 세팅
            String jsonAccount = new ObjectMapper().writeValueAsString(object);
            log.info("data {}", jsonAccount);

            kafkaTemplate.send(topicName, key, jsonAccount).whenComplete((result, error) -> {
                // success
                if(error == null) {
                    resultCod[0] = "0000";
                    log.info("AppService success {}", result);
                }
                // fail
                else {
                    resultCod[0] = "9999";
                    log.error("AppService fail {}", error);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultCod[0];
    }

}
