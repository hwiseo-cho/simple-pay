package com.hwiseo.app.controller;

import com.hwiseo.app.domain.Account;
import com.hwiseo.app.domain.Member;
import com.hwiseo.app.service.AppService;
import com.hwiseo.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * member 생성
     */
    @PostMapping("/app/member")
    public JSONObject createMember(@ModelAttribute("member") Member member) {

        JSONObject result = memberService.createMember(member);

        return result;
    }
}
