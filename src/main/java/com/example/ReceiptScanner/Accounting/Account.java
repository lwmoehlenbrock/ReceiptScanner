package com.example.ReceiptScanner.Accounting;

import com.example.ReceiptScanner.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account extends User {
//helloc
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    User user;

    @Id
    @GeneratedValue
    private Long id;
    private String accountType;
    private String balance;

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance += balance;
    }
}
