package com.example.backend2lab1.presentation;

import com.example.backend2lab1.application.AccountService;
import com.example.backend2lab1.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/all")
    public Iterable<Account> getAllAccounts() {
        return this.accountService.getAllAccounts();
    }

    @GetMapping("/hej")
    public String hej() {
        return "hej";
    }
}
