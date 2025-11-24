package com.example.niggapay.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class membersOfGroupBike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    String name;
    int money;
    int count;


    @JsonIgnore
    @ManyToOne
    bikeGroup bikeGroup;








    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public bikeGroup getBikeGroup() {
        return bikeGroup;
    }

    public void setBikeGroup(bikeGroup bikeGroup) {
        this.bikeGroup = bikeGroup;
    }
}
