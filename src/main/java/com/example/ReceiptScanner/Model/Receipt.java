package com.example.ReceiptScanner.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Receipt{

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
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
