package com.example.ReceiptScanner.Model;

import com.example.ReceiptScanner.Accounting.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Receipt extends User {

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    List<User> user;

    @Id
    @GeneratedValue
    private Long id;

    private double total;

    @OneToMany(mappedBy = "receipts")
    private List<Item> itemList;

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", total=" + total +
                ", itemList=" + itemList +
                '}';
    }

    public List<User> getUser() {
        return user;
    }

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
