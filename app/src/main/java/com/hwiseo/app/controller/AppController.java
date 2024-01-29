package com.hwiseo.app.controller;

import com.hwiseo.app.domain.Account;
import com.hwiseo.app.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AppController {

    private final AppService appService;

    @PostMapping("/app/account")
    public String createAccount(@ModelAttribute("account") Account account) {

        String resultCod = appService.createAccount(account);

        return resultCod;
    }
}
