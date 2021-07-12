package com.example.ReceiptScanner.Accounting;

public class Checking extends Account{
    private double balance;
    private String accountname;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }
}
