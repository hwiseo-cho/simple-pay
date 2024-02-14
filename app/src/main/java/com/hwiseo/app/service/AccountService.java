package com.hwiseo.app.service;

import com.hwiseo.app.common.Topic;
import com.hwiseo.app.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AppService appService;

    public JSONObject createAccount(Account account) {
        return appService.sendKafka(Topic.ACCOUNT,"createAccount", account);
    }

}
