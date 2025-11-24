package com.example.niggapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class count {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int countId;

    String  userName;
    int itemCout;


    @JsonIgnore
    @ManyToOne
    items items;

    public count() {
    }

    public count(int countId, String userName, int itemCout, com.example.niggapay.entity.items items) {
        this.countId = countId;
        this.userName = userName;
        this.itemCout = itemCout;
        this.items = items;
    }

    public int getCountId() {
        return countId;
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getItemCout() {
        return itemCout;
    }

    public void setItemCout(int itemCout) {
        this.itemCout = itemCout;
    }

    public com.example.niggapay.entity.items getItems() {
        return items;
    }

    public void setItems(com.example.niggapay.entity.items items) {
        this.items = items;
    }
}
