package com.example.ReceiptScanner;

import java.util.List;

public class Receipt extends User{
    private double total;
    private List<Item> itemList;
    private List<User> users; //needs to change

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
