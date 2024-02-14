package com.hwiseo.app.service;

import com.hwiseo.app.common.Topic;
import com.hwiseo.app.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final AppService appService;

    public JSONObject createMember(Member member) {
        return appService.sendKafka(Topic.MEMBER, "createMember" , member);
    }
}
