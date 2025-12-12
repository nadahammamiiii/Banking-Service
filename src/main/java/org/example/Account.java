package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements AccountService {

    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void deposit(int amount) {
        deposit(amount, LocalDate.now());
    }

    @Override
    public void withdraw(int amount) {
        withdraw(amount, LocalDate.now());
    }

    // **Nouvelle surcharge pour permettre de définir la date**
    public void deposit(int amount, LocalDate date) {
        validateAmount(amount);
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    public void withdraw(int amount, LocalDate date) {
        validateAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("DATE || AMOUNT || BALANCE");

        List<Transaction> reversed = new ArrayList<>(transactions);
        Collections.reverse(reversed);

        for (Transaction tx : reversed) {
            System.out.println(tx.date.format(FORMAT) + " || " + tx.amount + " || " + tx.balanceAfter);
        }
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    private static class Transaction {
        LocalDate date;
        int amount;
        int balanceAfter;

        Transaction(LocalDate date, int amount, int balanceAfter) {
            this.date = date;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
        }
    }
}
