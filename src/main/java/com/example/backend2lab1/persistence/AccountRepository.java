package com.example.backend2lab1.persistence;

import com.example.backend2lab1.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsAccountByName(String name);
    Account findAccountByName(String name);
}
