package com.hwiseo.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwiseo.app.domain.Account;
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

    public String createAccount(Account account) {
        final String[] resultCod = new String[1];

        try {
            String jsonAccount = new ObjectMapper().writeValueAsString(account);
            log.info("test {}", jsonAccount);

            kafkaTemplate.send("insert-account", jsonAccount).whenComplete((result, error) -> {
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
