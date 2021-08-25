package com.example.backend2lab1.presentation;

import com.example.backend2lab1.application.AccountService;
import com.example.backend2lab1.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/accounts/findbyname/{name}")
    public Account getAccountByName(@PathVariable("name") String name) {
        return this.accountService.getAccountByName(name);
    }

    @GetMapping("/openAccount/{name}")
    public String openAccount(@PathVariable("name") String name) {
        return accountService.openAccount(name) ? "Account added" : "Could not add account";
    }

    @GetMapping("/accounts/deposit/{name}/{amount}")
    public String depositToAccount(@PathVariable("name") String name, @PathVariable("amount") double amount) {
        accountService.deposit(name, amount);
        return "Successfully deposited " + amount + " kr into " + name + "s account.";
    }

    @GetMapping("/accounts/withdraw/{name}/{amount}")
    public String withdrawAmountFromAccount(@PathVariable("name") String name, @PathVariable("amount") double amount) {
        accountService.withdraw(name, amount);
        return "Successfully withdrew " + amount + " kr from " + name + "s account.";
    }
}
