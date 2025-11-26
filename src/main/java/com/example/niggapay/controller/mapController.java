package com.example.niggapay.controller;

import com.example.niggapay.entity.suggetionDto;
import com.example.niggapay.service.mapService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("map")
public class mapController {

    @Autowired
    mapService mapService;


    @GetMapping("suggestion")
    public ResponseEntity<?> suggestion(
            @RequestParam String query,
            @RequestParam String sessionToken

    ) throws JsonProcessingException {
        System.out.println("working suggestion");
        return mapService.getSuggestions(
                query,sessionToken
        );
    }



    @GetMapping("detail")
    public ResponseEntity<?> detail(
            @RequestParam String id,
            @RequestParam String sessionToken
    ) throws JsonProcessingException {
        return mapService.getPlaceDetails(id,sessionToken);
    }



    @GetMapping("getDistance")
    public ResponseEntity<?> getDistance(
            @RequestParam double long1, double lat1, double long2, double lat2
    ) throws Exception {
        return mapService.getDistance(
                long1,lat1,long2,lat2
        );
    }



    @GetMapping("calculateMoney")
    public ResponseEntity<?> calculateMoney(
            @RequestParam int km,
            @RequestParam boolean setting
    ){

        System.out.println("calculateMoney");
        double formula=0;
        if(setting){
            formula=28;
        }else{
         formula=Math.ceil((2.5)*km);
        }
        return  new  ResponseEntity<>((int)formula, HttpStatus.OK);
    }
}
