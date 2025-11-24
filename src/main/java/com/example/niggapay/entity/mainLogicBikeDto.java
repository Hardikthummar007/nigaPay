package com.example.niggapay.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class mainLogicBikeDto {



    int count;
    int total_cost;
    int divideBy;
    String groupId;
    Map<String,Integer> studentsWithCount;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public int getDivideBy() {
        return divideBy;
    }

    public void setDivideBy(int divideBy) {
        this.divideBy = divideBy;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Map<String, Integer> getStudentsWithCount() {
        return studentsWithCount;
    }

    public void setStudentsWithCount(Map<String, Integer> studentsWithCount) {
        this.studentsWithCount = studentsWithCount;
    }
}
