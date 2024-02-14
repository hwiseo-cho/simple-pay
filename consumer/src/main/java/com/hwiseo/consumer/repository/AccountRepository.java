package com.hwiseo.consumer.repository;

import com.hwiseo.consumer.entity.Account;
import com.hwiseo.consumer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AccountRepository {

    public void createAccount(Account account);

}
