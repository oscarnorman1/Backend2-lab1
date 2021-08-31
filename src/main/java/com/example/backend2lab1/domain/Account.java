package com.example.backend2lab1.domain;

import com.example.backend2lab1.persistence.AccountRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    public Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount < 0)
            throw new IllegalArgumentException("Could not deposit a negative amount");

        this.balance += amount;
    }

    public void withdraw(double amount) {
        if(amount > this.balance)
            throw new IllegalArgumentException("Could not withdraw amount larger than balance");

        if(amount < 0)
            throw new IllegalArgumentException("Could not withdraw a negative amount");

        this.balance -= amount;
    }
}
