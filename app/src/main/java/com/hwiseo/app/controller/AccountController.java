package com.hwiseo.app.controller;

import com.hwiseo.app.domain.Account;
import com.hwiseo.app.domain.Member;
import com.hwiseo.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    /**
     * account 생성
     */
    @PostMapping("/app/account")
    public JSONObject createAccount(@ModelAttribute("account")Account account) {
        JSONObject result = accountService.createAccount(account);
        return result;
    }
}
