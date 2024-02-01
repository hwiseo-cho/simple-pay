package com.hwiseo.app.controller;

import com.hwiseo.app.domain.Account;
import com.hwiseo.app.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    @Test
    public void 회원_등록() {
        Member member = new Member();
        member.setMemberName("홍길동");
        member.setPhone("01033338888");

        memberController.createMember(member);
    }
}