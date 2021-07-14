package com.example.ReceiptScanner.Model;

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

    String name;
    private double budget;
    private double savingsGoal;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    @OneToMany(mappedBy = "user")
    private List<Receipt> receipts;

    public User() {

    }

    public User(String name1, double budget1, double savingsGoal1, List<Account> accounts1, List<Receipt> receipts1) {
        this.name = name1;
        this.budget = budget1;
        this.savingsGoal = savingsGoal1;
        this.accounts = accounts1;
        this.receipts = receipts1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", savingsGoal=" + savingsGoal +
                ", accounts=" + accounts +
                ", reciepts=" + receipts +
                '}';
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
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
