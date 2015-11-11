package etu.upmc.fr.repository;


import etu.upmc.fr.entity.Account;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccountRepositoryImpl implements AccountRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Account saveAndCryptPassword(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        entityManager.persist(account);

        return account;
    }

}
