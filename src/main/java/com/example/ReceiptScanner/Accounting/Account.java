package com.example.ReceiptScanner.Accounting;

import com.example.ReceiptScanner.Model.User;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Account extends User {
    List<Savings> savingsList;
    List<Checking> checkingList;

    public List<Savings> getSavingsList() {
        return savingsList;
    }

    public List<Checking> getCheckingList() {
        return checkingList;
    }
}
