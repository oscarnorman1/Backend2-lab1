package com.example.backend2lab1.persistence;


import com.example.backend2lab1.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends CrudRepository<Account, Long> {
     boolean existsAccountByName(String name);
}
