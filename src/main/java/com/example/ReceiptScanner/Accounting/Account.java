package com.example.ReceiptScanner.Accounting;

import java.util.List;

public class Account {
    List<Savings> savingsList;
    List<Checking> checkingList;

    public List<Savings> getSavingsList() {
        return savingsList;
    }

    public List<Checking> getCheckingList() {
        return checkingList;
    }
}
