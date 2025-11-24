package com.example.niggapay.controller;

import com.example.niggapay.entity.*;
import com.example.niggapay.service.commonService;
import com.example.niggapay.service.suggestionSystemService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class commonController {
    @Autowired
    commonService commonService;
    @Autowired
    suggestionSystemService suggestionSystemService;

    @Value("${INSTANCE_ID:unknown}")
    String instance;

    @GetMapping("/test")
    String  test(){
        System.out.println(instance);
        return "isha";
    }
    @PostMapping("generateBill")
    ResponseEntity<?> genereteBill(@RequestBody paymentRequestDto bill){
        return commonService.createBill(bill);
    }

    @PostMapping("addUser")
    ResponseEntity<?> addUser(@RequestBody user user){
        return commonService.addUser(user);
    }


    @GetMapping("getBill")
    paymentRequestResponseToSeeTheBill getBill(@RequestParam int billId){
        return commonService.getBill(billId);
    }

    @GetMapping("/top")
    public List<String> getTopSuggestions(@RequestParam String  category) {
        return suggestionSystemService.getTop5FrequentlyUsedItem(category);
    }

    @GetMapping("/hasToPay")
    public List<hasToPay> getHasToPay(@RequestParam String userName){
        return commonService.getHasToPay(userName);
    }
    @GetMapping("/hasToReceive")
    public  List<hasToReceive> getHasToReceive(@RequestParam String userName){
        return commonService.getHasToReceive(userName);
    }


    @GetMapping("/test1")
    public void test(@RequestParam String userName){
        commonService.test1(userName);
    }




    @PostMapping("/sendMoney")
    public ResponseEntity<?> sendMoney(
            @RequestParam int id
    ){
        return commonService.sendMoney(id);
    }








//    htr pending


    @GetMapping("/getPendingHtr")
    public List<hasToReceive> getPendingHtr(@RequestParam String userName){

        return commonService.getPendingHasToReceive(userName);

    }


    @GetMapping("/getPendingHtp")
    public List<hasToPay> getPendingHtp(@RequestParam String userName){

        return commonService.getPendingHasToPay(userName);

    }


//    public  ResponseEntity<?> changeStatusHtp(String sending,String receiving,int htpId,String yesOrNo){

        @PostMapping("/changeStatusOfApprovementOfHtp")
    public  ResponseEntity<?> changeStatusOfApprovementOfHtp(

            @RequestParam int htpId,
            @RequestParam String yesOrNo
    ){

        return  commonService.changeStatusHtp(
               htpId,yesOrNo
        );
    }


    @PostMapping("/changeStatusOfApprovementOfHtr")
    public  ResponseEntity<?> changeStatusOfApprovementOfHtr(
            @RequestParam int htrId,
            @RequestParam String yesOrNo
    ){
        return  commonService.changeStatusHtr(htrId,yesOrNo);
    }


    @GetMapping("getbystatusOfApprovementPassAndStatusOfPaymentPendingHtp")
    public ResponseEntity<?> getbystatusOfApprovementPassAndStatusOfPaymentPendinghtp(
            @RequestParam String userName

    ){
        return commonService.getHtpApprovedAndPendingHtp(userName);
    }

    @GetMapping("getbystatusOfApprovementPassAndStatusOfPaymentPendingHtr")
    public ResponseEntity<?> getbystatusOfApprovementPassAndStatusOfPaymentPendinghtr(
            @RequestParam String userName
    ){
        return commonService.getHtpApprovedAndPendingHtr(userName);
    }

    @PostMapping("/confirmationFromReciver")
    public ResponseEntity<?> confirmationFromReciver(@RequestParam int id){
        return commonService.confirmationOfMoneyLaundry(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        return  commonService.login(username,password);
    }





}
