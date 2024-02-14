package com.hwiseo.app.controller;

import com.hwiseo.app.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Test
    void 계좌_생성() {
        Account account = new Account();
        account.setMemberId(1L);
        account.setAccountNo("12132123");
        account.setBankName("신한");
        account.setBankCod("000");
        account.setUserName("조휘서");

        accountController.createAccount(account);
    }
}