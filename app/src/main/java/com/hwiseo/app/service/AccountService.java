package com.hwiseo.app.service;

import com.hwiseo.app.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AppService appService;

    public String createAccount(Account account) {

        return "";
    }

}
