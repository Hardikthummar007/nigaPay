package com.example.niggapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class sharedItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int sharedItemsId;

    String  itemName;
    int totalAmountOfSharedItem;

    @ElementCollection
    List<String > sharedUser;

    @JsonIgnore
    @ManyToOne
    paymentRequest paymentRequest;


    public int getTotalAmountOfSharedItem() {
        return totalAmountOfSharedItem;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSharedItemsId() {
        return sharedItemsId;
    }

    public void setSharedItemsId(int sharedItemsId) {
        this.sharedItemsId = sharedItemsId;
    }

    public com.example.niggapay.entity.paymentRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(com.example.niggapay.entity.paymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public void setTotalAmountOfSharedItem(int totalAmountOfSharedItem) {
        this.totalAmountOfSharedItem = totalAmountOfSharedItem;
    }

    public List<String> getSharedUser() {
        return sharedUser;
    }

    public void setSharedUser(List<String> sharedUser) {
        this.sharedUser = sharedUser;
    }
}
