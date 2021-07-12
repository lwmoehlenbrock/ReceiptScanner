package com.example.ReceiptScanner;

import com.example.ReceiptScanner.Accounting.*;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {
    private @Id @GeneratedValue  Long userID;
    //private String uniqueIdentifier;

    private double budget;
    private double savingsGoal;
    private List<Account> accounts;
    private List<Receipt> reciepts;



    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Receipt> getReciepts() {
        return reciepts;
    }

    public void setReciepts(List<Receipt> reciepts) {
        this.reciepts = reciepts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }

    public void setSavingsGoal(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }




}
