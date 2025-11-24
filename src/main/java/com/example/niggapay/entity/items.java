package com.example.niggapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int itemId;
    String itemName;
    int singleItemAmount;

    @JsonIgnore
    @ManyToOne
    paymentRequest paymentRequest;

    @OneToMany(mappedBy = "items",cascade = CascadeType.ALL,orphanRemoval = true)
    List<count> userItemCount=new ArrayList<>();



    public items() {
    }



    public items(int itemId, String itemName, int singleItemAmount, com.example.niggapay.entity.paymentRequest paymentRequest, List<count> userItemCount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.singleItemAmount = singleItemAmount;
        this.paymentRequest = paymentRequest;
        this.userItemCount = userItemCount;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSingleItemAmount() {
        return singleItemAmount;
    }

    public void setSingleItemAmount(int singleItemAmount) {
        this.singleItemAmount = singleItemAmount;
    }

    public com.example.niggapay.entity.paymentRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(com.example.niggapay.entity.paymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public List<count> getUserItemCount() {
        return userItemCount;
    }

    public void setUserItemCount(List<count> userItemCount) {
        this.userItemCount = userItemCount;
    }
}
