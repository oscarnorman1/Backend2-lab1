package com.example.backend2lab1.application;

import com.example.backend2lab1.domain.Account;
import com.example.backend2lab1.domain.Validator;
import com.example.backend2lab1.persistence.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;


@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public Account getAccountByName(String name) {
        if(accountRepository.existsAccountByName(name))
            return accountRepository.findAccountByName(name);

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hittar ej personen");
    }

    @Transactional
    public boolean openAccount(String name) {
        if(accountRepository.existsAccountByName(name))
            return false;

        accountRepository.save(new Account(name));
        return true;
    }

    @Transactional
    public void deposit(String name, double amount) {
        Account acc = accountRepository.findAccountByName(name);

        if(!Validator.isNull(acc))
            acc.deposit(amount);
    }

    @Transactional
    public void withdraw(String name, double amount) {
        Account acc = accountRepository.findAccountByName(name);

        if(!Validator.isNull(acc))
            acc.withdraw(amount);
    }
}
