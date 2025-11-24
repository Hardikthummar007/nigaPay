package com.example.niggapay.service;

import com.example.niggapay.entity.bikeGroup;
import com.example.niggapay.entity.mainLogicBikeDto;
import com.example.niggapay.entity.membersOfGroupBike;
import com.example.niggapay.repository.groupRepo;
import com.example.niggapay.repository.membersOfGroupRepo;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class bikeService {


    @Autowired
    groupRepo  groupRepo;

    @Autowired
    membersOfGroupRepo membersOfGroupRepo;




    public String createGroup(bikeGroup bg){
        List<membersOfGroupBike> listmembersOfGroupBikes=bg.getMembersOfGroupBikeList();
        for(int i=0;i<listmembersOfGroupBikes.size();i++){
            membersOfGroupBike temp=listmembersOfGroupBikes.get(i);
            temp.setBikeGroup(bg);
        }
        groupRepo.save(bg);
        return "success";
    }

    public List<bikeGroup> getGroups(String  name){
        List<bikeGroup> groups=groupRepo.findByOwnerOfGroup(name);
        return groups;
    }

    public ResponseEntity<?> getGroupById(String  id){

        Optional<bikeGroup> temp= groupRepo.findById(id);
        if(temp.isPresent()){
            return  new  ResponseEntity<>(temp.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("no group exist with this name",HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> mainLogicBIke(mainLogicBikeDto dto){

        int count=dto.getCount();
        int cost=dto.getTotal_cost();
        int divideBy=dto.getDivideBy();
        Map<String,Integer> usersWithCount=dto.getStudentsWithCount();
        int perPersonBikeCost=cost/divideBy;
        String   groupId=dto.getGroupId();

        Optional<bikeGroup>   bikeGroup=groupRepo.findById(dto.getGroupId());

        if(bikeGroup.isPresent()){

            bikeGroup bg=bikeGroup.get();

            List<membersOfGroupBike>  listmembersOfGroupBikes=bg.getMembersOfGroupBikeList();
            for(Map.Entry<String, Integer> entry:usersWithCount.entrySet()){

                membersOfGroupBike mb= membersOfGroupRepo.findByUsernameGroupid(
                        groupId,
                        entry.getKey()
                );

                int prevCount=mb.getCount();
                int preMoney=mb.getMoney();

                mb.setCount(mb.getCount()+entry.getValue());
                mb.setMoney(preMoney+(perPersonBikeCost*entry.getValue()));
                membersOfGroupRepo.save(mb);

            }
            return new  ResponseEntity<>("success",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("no group exist with this name",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> clearBill(int id){

        Optional<membersOfGroupBike> temp = membersOfGroupRepo.findById(id);

        if(temp.isPresent()){
            temp.get().setCount(0);
            temp.get().setMoney(0);
            membersOfGroupRepo.save(temp.get());
        return new  ResponseEntity<>("success",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("no member with this id exist ",HttpStatus.NOT_FOUND);

        }



    }




}
