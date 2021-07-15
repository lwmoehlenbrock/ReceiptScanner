package com.example.ReceiptScanner.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public enum Categories {
    FOOD, ENTERTAINMENT, BILLS, CLOTHING, MISC
}



//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int categoryId;
//    private int userId;
//    private int date;
//    private String title;
//    private String description;
//    private Double totalExpense;
//
//    public Category(Integer categoryId, Integer userId, String title, String description, Double totalExpense, int date) {
//        this.categoryId = categoryId;
//        this.userId = userId;
//        this.title = title;
//        this.description = description;
//        this.totalExpense = totalExpense;
//        this.date = date;
//    }
//
//    public Integer getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Integer categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Double getTotalExpense() {
//        return totalExpense;
//    }
//
//    public void setTotalExpense(Double totalExpense) {
//        this.totalExpense = totalExpense;
//    }
//
//    public int getDate() {
//        return date;
//    }
//
//    public void setDate(int date) {
//        this.date = date;
//    }
