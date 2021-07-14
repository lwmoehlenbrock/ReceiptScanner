package com.example.ReceiptScanner.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Account extends User {

    public Account() {}

    public Account(String accountType, String accountName, double balance){
        super();
        this.accountName = super.name;
        this.accountType= accountType;
        this.balance = balance;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    User user;

    @Id
    @GeneratedValue
    private Long id;
    private String accountType;
    private String accountName;
    private double balance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }
}
