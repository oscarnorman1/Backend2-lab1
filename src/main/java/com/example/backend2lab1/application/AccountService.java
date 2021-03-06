package com.example.backend2lab1.application;

import com.example.backend2lab1.domain.Account;
import com.example.backend2lab1.domain.IRiskClient;
import com.example.backend2lab1.domain.Validator;
import com.example.backend2lab1.persistence.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final IRiskClient riskAssessment;

    public AccountService(AccountRepository accountRepository, IRiskClient riskAssessment) {
        this.accountRepository = accountRepository;
        this.riskAssessment = riskAssessment;
    }

    @Transactional
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public Account getAccountByName(String name) {
        if(accountRepository.existsAccountByName(name))
            return accountRepository.findAccountByName(name);

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hittar ej personen");
    }

    @Transactional
    public Account openAccount(String name) {
        if(accountRepository.existsAccountByName(name))
            throw new IllegalArgumentException("Account with name " + name + " already exists");

        if (!riskAssessment.validate(name))
            throw new IllegalArgumentException("Bad credit score");

        Account newAcc = new Account(name);
        accountRepository.save(newAcc);
        return newAcc; // Bara för att visa
    }

    @Transactional
    public Account deposit(String name, double amount) {
        Account acc = accountRepository.findAccountByName(name);

        if(!Validator.isNull(acc)) {
            acc.deposit(amount);
            return acc;
        }
        return openAccount(name);
    }

    @Transactional
    public Account withdraw(String name, double amount) {
        Account acc = accountRepository.findAccountByName(name);

        if(!Validator.isNull(acc)) {
            acc.withdraw(amount);
            return acc;
        }
        throw new IllegalArgumentException("Account does not exist");
    }

    public IRiskClient getRiskAssessment() {
        return this.riskAssessment;
    }
}
