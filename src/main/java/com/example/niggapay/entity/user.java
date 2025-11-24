package com.example.niggapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class user {

    @Id
    String userName;
    String password;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<paymentRequest> paymentRequests=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<hasToPay> hasToPays=new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<hasToReceive> hasToReceives=new ArrayList<>();
    public user() {
    }

    public user(String userName, String password, List<paymentRequest> paymentRequests, List<hasToPay> hasToPays, List<hasToReceive> hasToReceives) {
        this.userName = userName;
        this.password = password;
        this.paymentRequests = paymentRequests;
        this.hasToPays = hasToPays;
        this.hasToReceives = hasToReceives;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<paymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

    public void setPaymentRequests(List<paymentRequest> paymentRequests) {
        this.paymentRequests = paymentRequests;
    }

    public List<hasToPay> getHasToPays() {
        return hasToPays;
    }

    public void setHasToPays(List<hasToPay> hasToPays) {
        this.hasToPays = hasToPays;
    }

    public List<hasToReceive> getHasToReceives() {
        return hasToReceives;
    }

    public void setHasToReceives(List<hasToReceive> hasToReceives) {
        this.hasToReceives = hasToReceives;
    }
}
