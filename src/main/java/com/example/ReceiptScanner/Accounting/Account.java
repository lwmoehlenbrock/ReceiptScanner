package com.example.ReceiptScanner.Accounting;

import com.example.ReceiptScanner.User;

import java.util.List;

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
