package com.hwiseo.app.controller;

import com.hwiseo.app.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppControllerTest {

    @Autowired
    private AppController appController;

    @Test
    public void 계좌_등록() {
        Account account = new Account();

        account.setMemberId("1");
        account.setAccountNo("1234");
        account.setUserName("1234");
        account.setBankCod("1");
        account.setBankName("신한");

        for(int i=0; i< 10; i++) {
            appController.createAccount(account);
        }
    }
}