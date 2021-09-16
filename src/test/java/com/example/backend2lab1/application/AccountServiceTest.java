package com.example.backend2lab1.application;

import com.example.backend2lab1.domain.Account;
import com.example.backend2lab1.domain.IRiskClient;
import com.example.backend2lab1.persistence.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    AccountService accountService;

    AccountRepository mockRepository = mock(AccountRepository.class);

    IRiskClient mockClient = mock(IRiskClient.class);

    @BeforeEach
    void init() {
            accountService = new AccountService(mockRepository, name -> {
                return name.charAt(name.length()-1) % 2 == 0; // löjl
            });
    }

    @Test
    void openAccountShouldPass() {
        when(mockRepository.existsAccountByName("Leif")).thenReturn(false);
        Account test = accountService.openAccount("Leif");

        assertEquals(test.getName(), "Leif");

        verify(mockRepository).existsAccountByName(any());
    }

    @Test
    void openAccountShouldThrowException() {
        when(mockRepository.existsAccountByName("Svante")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> accountService.openAccount("Svante"));
        verify(mockRepository).existsAccountByName(any());
    }

    @Test
    void depositNegativeAmountShouldThrowException() {
        when(mockRepository.findAccountByName("Leif")).thenReturn(new Account("Leif"));

        assertThrows(IllegalArgumentException.class, () -> accountService.deposit("Leif", -200));
        verify(mockRepository).findAccountByName(any());
    }

    @Test
    void withdrawAmountLargerThanBalanceShouldThrowException() {
        when(mockRepository.findAccountByName("Leif")).thenReturn(new Account("Leif"));

        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw("Leif", 90000));
        verify(mockRepository).findAccountByName(any());
    }

    @Test
    void openAccountShouldThrowBadCreditException() {
        when(mockRepository.existsAccountByName("a")).thenReturn(false);

        try {
            accountService.openAccount("a");
        } catch (Exception e) {
            assertEquals("Bad credit score", e.getMessage());
        }

        assertThrows(IllegalArgumentException.class, () -> accountService.openAccount("a"));

        verify(mockRepository, times(2)).existsAccountByName("a");
    }

}