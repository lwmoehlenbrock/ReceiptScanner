package com.example.ReceiptScanner.Accounting;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Checking extends Account{
    private double balance;
    private String accountname;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    Account account;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Account getAccount() {
        return account;
    }
}
