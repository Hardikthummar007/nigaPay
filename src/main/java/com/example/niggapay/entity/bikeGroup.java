package com.example.niggapay.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@Entity
public class bikeGroup {

    @Id
    String groupName;

    int km;
    String source;
    String destination;
    int totalMoneyOfSourceToDestination;
    String ownerOfGroup;



    @OneToMany(
            mappedBy = "bikeGroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<membersOfGroupBike>  membersOfGroupBikeList;


    public String getOwnerOfGroup() {
        return ownerOfGroup;
    }

    public void setOwnerOfGroup(String ownerOfGroup) {
        this.ownerOfGroup = ownerOfGroup;
    }

    public List<membersOfGroupBike> getMembersOfGroupBikeList() {
        return membersOfGroupBikeList;
    }

    public void setMembersOfGroupBikeList(List<membersOfGroupBike> membersOfGroupBikeList) {
        this.membersOfGroupBikeList = membersOfGroupBikeList;
    }

    public int getTotalMoneyOfSourceToDestination() {
        return totalMoneyOfSourceToDestination;
    }

    public void setTotalMoneyOfSourceToDestination(int totalMoneyOfSourceToDestination) {
        this.totalMoneyOfSourceToDestination = totalMoneyOfSourceToDestination;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
