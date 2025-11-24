package com.example.niggapay.entity;


import java.util.ArrayList;
import java.util.List;

public class paymentRequestDto {
    public String payerUsername;
    public int totalAmount;
    public String category;
    public List<items> items;

    public List<sharedItems> sharedItems;

    public String modeOfPayment;

    List<String> friends=new ArrayList<>();

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<com.example.niggapay.entity.sharedItems> getSharedItems() {
        return sharedItems;
    }

    public void setSharedItems(List<com.example.niggapay.entity.sharedItems> sharedItems) {
        this.sharedItems = sharedItems;
    }

    public String getPayerUsername() {
        return payerUsername;
    }

    public void setPayerUsername(String payerUsername) {
        this.payerUsername = payerUsername;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<com.example.niggapay.entity.items> getItems() {
        return items;
    }

    public void setItems(List<com.example.niggapay.entity.items> items) {
        this.items = items;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }
}
