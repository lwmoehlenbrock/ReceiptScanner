package com.example.ReceiptScanner.Accounting;

import com.example.ReceiptScanner.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Account extends User {

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    User user;

    @OneToMany(mappedBy = "account")
    List<Savings> savingsList;

    @OneToMany(mappedBy = "account")
    List<Checking> checkingList;

    @Override
    public String toString() {
        return "Account{" +
                "savingsList=" + savingsList +
                ", checkingList=" + checkingList +
                '}';
    }

    public List<Savings> getSavingsList() {
        return savingsList;
    }

    public List<Checking> getCheckingList() {
        return checkingList;
    }
}
