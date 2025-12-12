package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testDepositPositive() {
        AccountService account = new Account();
        assertDoesNotThrow(() -> account.deposit(1000));
    }

    @Test
    void testInvalidDeposit() {
        AccountService account = new Account();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-20));
        assertEquals("Amount must be positive", e.getMessage());
    }

    @Test
    void testWithdraw() {
        AccountService account = new Account();
        account.deposit(500);
        assertDoesNotThrow(() -> account.withdraw(200));
    }

    @Test
    void testWithdrawInsufficientBalance() {
        AccountService account = new Account();
        account.deposit(100);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(200));
        assertEquals("Insufficient balance", e.getMessage());
    }
}
