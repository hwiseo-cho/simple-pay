package com.hwiseo.consumer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    private String memberId;

    private String accountNo;

    private String userName;

    private String bankCod;

    private String bankName;
}
