package com.example.niggapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class hasToReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int htrId;

    int amount;
    String nameOfPersonfromWhomMoneyINeedToReciveMoney;
    String statusOfPayment;
    LocalDate date;


    String statusOfApprovement;
    @JsonIgnore
    @ManyToOne
    user user;


    int htpId;

    public int getHtpId() {
        return htpId;
    }

    public void setHtpId(int htpId) {
        this.htpId = htpId;
    }

    public hasToReceive() {
    }

    public hasToReceive(int htrId, int amount, String nameOfPersonfromWhomMoneyINeedToReciveMoney, String statusOfPayment, LocalDate date, String statusOfApprovement, com.example.niggapay.entity.user user) {
        this.htrId = htrId;
        this.amount = amount;
        this.nameOfPersonfromWhomMoneyINeedToReciveMoney = nameOfPersonfromWhomMoneyINeedToReciveMoney;
        this.statusOfPayment = statusOfPayment;
        this.date = date;
        this.statusOfApprovement = statusOfApprovement;
        this.user = user;
    }

    public String getStatusOfApprovement() {
        return statusOfApprovement;
    }

    public void setStatusOfApprovement(String statusOfApprovement) {
        this.statusOfApprovement = statusOfApprovement;
    }

    public int getHtrId() {
        return htrId;
    }

    public void setHtrId(int htrId) {
        this.htrId = htrId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNameOfPersonfromWhomMoneyINeedToReciveMoney() {
        return nameOfPersonfromWhomMoneyINeedToReciveMoney;
    }

    public void setNameOfPersonfromWhomMoneyINeedToReciveMoney(String nameOfPersonfromWhomMoneyINeedToReciveMoney) {
        this.nameOfPersonfromWhomMoneyINeedToReciveMoney = nameOfPersonfromWhomMoneyINeedToReciveMoney;
    }

    public String getStatusOfPayment() {
        return statusOfPayment;
    }

    public void setStatusOfPayment(String statusOfPayment) {
        this.statusOfPayment = statusOfPayment;
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
