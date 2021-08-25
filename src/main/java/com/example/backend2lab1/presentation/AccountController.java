package com.example.backend2lab1.presentation;

import com.example.backend2lab1.application.AccountService;
import com.example.backend2lab1.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/all")
    public Iterable<Account> getAccountName() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/add/{name}")
    public String addAccount(@PathVariable("name") String name) {
        if(this.accountService.addAccount(name))
            return "success!";
        return "fail...";
    }
}
