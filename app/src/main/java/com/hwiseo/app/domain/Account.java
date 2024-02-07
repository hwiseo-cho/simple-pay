package com.hwiseo.app.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Account {

    private Long memberId;
    private String accountNo;
    private String userName;
    private String bankCod;
    private String bankName;

}
