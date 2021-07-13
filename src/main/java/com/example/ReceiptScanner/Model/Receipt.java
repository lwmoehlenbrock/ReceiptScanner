package com.example.ReceiptScanner.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Receipt extends User {

    @Id
    @GeneratedValue
    private Long id;

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
