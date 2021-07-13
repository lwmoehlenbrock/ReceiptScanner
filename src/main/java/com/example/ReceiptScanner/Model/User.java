package com.example.ReceiptScanner.Model;

import com.example.ReceiptScanner.Accounting.*;
import com.example.ReceiptScanner.Model.Receipt;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double budget;
    private double savingsGoal;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    @OneToMany(mappedBy = "user")
    private List<Receipt> reciepts;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", savingsGoal=" + savingsGoal +
                ", accounts=" + accounts +
                ", reciepts=" + reciepts +
                '}';
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
