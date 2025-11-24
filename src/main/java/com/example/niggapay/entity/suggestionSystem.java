package com.example.niggapay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class suggestionSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int ssId;

    String category;
    String  itemName;
    int frequency;

    public int getSsId() {
        return ssId;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSsId(int ssId) {
        this.ssId = ssId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
