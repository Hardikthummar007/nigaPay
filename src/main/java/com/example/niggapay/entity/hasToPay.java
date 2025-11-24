package com.example.niggapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class hasToPay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int htpId;

    int amount;
    String owner;
    String statusOfPayment;
    String statusOfApprovement;
    LocalDate date;

    @JsonIgnore
    @ManyToOne
    user user;



    int htrId;


    public int getHtrId() {
        return htrId;
    }

    public void setHtrId(int htrId) {
        this.htrId = htrId;
    }

    public hasToPay() {
    }

    public hasToPay(int htpId, int amount, String owner, String statusOfPayment, String statusOfApprovement, LocalDate date, com.example.niggapay.entity.user user) {
        this.htpId = htpId;
        this.amount = amount;
        this.owner = owner;
        this.statusOfPayment = statusOfPayment;
        this.statusOfApprovement = statusOfApprovement;
        this.date = date;
        this.user = user;
    }

    public int getHtpId() {
        return htpId;
    }

    public void setHtpId(int htpId) {
        this.htpId = htpId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatusOfPayment() {
        return statusOfPayment;
    }

    public void setStatusOfPayment(String statusOfPayment) {
        this.statusOfPayment = statusOfPayment;
    }

    public String getStatusOfApprovement() {
        return statusOfApprovement;
    }

    public void setStatusOfApprovement(String statusOfApprovement) {
        this.statusOfApprovement = statusOfApprovement;
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
}
