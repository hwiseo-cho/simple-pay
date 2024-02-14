package com.hwiseo.consumer.repository;

import com.hwiseo.consumer.entity.Account;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositroyImpl implements AccountRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AccountRepositroyImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void createAccount(Account account) {
        em.persist(account);
    }
}
