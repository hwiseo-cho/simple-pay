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

    /**
     * kafka 전송(producer)
     */
    public JSONObject sendKafka(Topic topic, String key, Object object) {
        JSONObject json = new JSONObject();
        final String[] resultStr = new String[2];

        try {
            // 받아온 토픽으로 설정
            String topicName = topic.getTopicName();

            // 받아온 값 세팅
            String jsonAccount = new ObjectMapper().writeValueAsString(object);
            log.info("data {}", jsonAccount);

            kafkaTemplate.send(topicName, key, jsonAccount).whenComplete((result, error) -> {
                // success
                if(error == null) {
                    resultStr[0] = "0000";
                    resultStr[1] = "success";
                    log.info("AppService success {}", result);
                }
                // fail
                else {
                    resultStr[0] = "9999";
                    resultStr[1] = error.getMessage();
                    log.error("AppService fail {}", error);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        json.put("result", resultStr[0]);
        json.put("message", resultStr[1]);

        return json;
    }

}
