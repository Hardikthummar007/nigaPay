package com.example.niggapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class paymentRequestResponseToSeeTheBill {


    int paymentRequestId;

    int actualAmount;
    String modeOfPayment;
    String category;
    String status;
    LocalDate date;




    user user;

    List<items> items=new ArrayList<>();

    List<sharedItems> sharedItems=new ArrayList<>();


    public paymentRequestResponseToSeeTheBill() {
    }

    public paymentRequestResponseToSeeTheBill(int paymentRequestId, int actualAmount, String modeOfPayment, String category, String status, LocalDate date, com.example.niggapay.entity.user user, List<com.example.niggapay.entity.items> items, List<com.example.niggapay.entity.sharedItems> sharedItems) {
        this.paymentRequestId = paymentRequestId;
        this.actualAmount = actualAmount;
        this.modeOfPayment = modeOfPayment;
        this.category = category;
        this.status = status;
        this.date = date;
        this.user = user;
        this.items = items;
        this.sharedItems = sharedItems;
    }

    public List<com.example.niggapay.entity.sharedItems> getSharedItems() {
        return sharedItems;
    }

    public void setSharedItems(List<com.example.niggapay.entity.sharedItems> sharedItems) {
        this.sharedItems = sharedItems;
    }

    public int getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(int paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public com.example.niggapay.entity.user getUser() {
        return user;
    }

    public void setUser(com.example.niggapay.entity.user user) {
        this.user = user;
    }

    public List<com.example.niggapay.entity.items> getItems() {
        return items;
    }

    public void setItems(List<com.example.niggapay.entity.items> items) {
        this.items = items;
    }
}
