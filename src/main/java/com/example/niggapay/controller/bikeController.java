package com.example.niggapay.controller;


import com.example.niggapay.entity.bikeGroup;
import com.example.niggapay.entity.mainLogicBikeDto;
import com.example.niggapay.service.bikeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bike")
public class bikeController {

    @Autowired
    bikeService bikeService;

    @PostMapping("addGroup")
    ResponseEntity<?> addGroup(@RequestBody bikeGroup bg)
    {
        String response= bikeService.createGroup(bg);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("getAllGroups")
    ResponseEntity<?> getAllGroups(
            @RequestParam String  name
    ){
        List<bikeGroup> groups = bikeService.getGroups(name);
        if(groups!=null){

        return  new ResponseEntity<>(groups, HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("no group created",HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("getGroupById")
    ResponseEntity<?> getGroupById(@RequestParam("id") String id){
        return   bikeService.getGroupById(id);
    }

    @PostMapping("addExpenses")
    ResponseEntity<?> addExpenses(

            @RequestBody mainLogicBikeDto dto
    ){
        return bikeService.mainLogicBIke(dto);
    }



    @PostMapping("clearBikeBill")
    ResponseEntity<?> clearBikeBill(
            @RequestParam int id
    ){
        return bikeService.clearBill(id);
    }





}
