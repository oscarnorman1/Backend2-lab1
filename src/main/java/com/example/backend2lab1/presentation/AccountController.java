package com.example.backend2lab1.presentation;

import com.example.backend2lab1.application.AccountService;
import com.example.backend2lab1.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/all")
    public List<Account> getAllAccounts() {
        return this.accountService.getAllAccounts();
    }

    @GetMapping("/hej")
    public String hej() {
        return "hej";
    }

    @GetMapping("/accounts/findbyname/{name}")
    public Account getAccountByName(@PathVariable("name") String name) {
        return this.accountService.getAccountByName(name);
    }

    @GetMapping("/openAccount/{name}")
    public Account openAccount(@PathVariable("name") String name) {
        return accountService.openAccount(name);
    }

    @GetMapping("/accounts/deposit/{name}/{amount}")
    public Account depositToAccount(@PathVariable("name") String name, @PathVariable("amount") double amount) {
        return accountService.deposit(name, amount);
    }

    @GetMapping("/accounts/withdraw/{name}/{amount}")
    public Account withdrawAmountFromAccount(@PathVariable("name") String name, @PathVariable("amount") double amount) {
        return accountService.withdraw(name, amount);
    }
}
