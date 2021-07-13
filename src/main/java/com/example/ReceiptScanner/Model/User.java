package com.example.ReceiptScanner.Model;

import com.example.ReceiptScanner.Accounting.*;
import com.example.ReceiptScanner.Model.Receipt;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

     @Id
     @GeneratedValue
     private Long id;

    private double budget;
    private double savingsGoal;

    // establish relationship
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
