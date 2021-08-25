package com.example.backend2lab1.application;

import com.example.backend2lab1.domain.Account;
import com.example.backend2lab1.persistence.AccountRepository;

public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Iterable<Account> getAllAccounts() {
        return repository.findAll();
    }

    public boolean addAccount(String name) {
        if(this.repository.existsAccountByName(name)) {
            this.repository.save(new Account(name));
            return true;
        }
        return false;
    }
}
