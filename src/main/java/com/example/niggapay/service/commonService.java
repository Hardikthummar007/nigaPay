package com.example.niggapay.service;

import com.example.niggapay.entity.*;
import com.example.niggapay.repository.hasToPayRepo;
import com.example.niggapay.repository.hasToReceiveRepo;
import com.example.niggapay.repository.paymentRequestRepo;
import com.example.niggapay.repository.userRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class commonService {


    @Autowired
    paymentRequestRepo paymentRequestRepo;

    @Autowired
    hasToPayRepo hasToPayRepo;

    @Autowired
    userRepo userRepo;

    @Autowired
    suggestionSystemService suggestionSystemService;

    @Autowired
    hasToReceiveRepo hasToReceiveRepo;




    @Transactional
  public   ResponseEntity<?> createBill( paymentRequestDto bill){

        Optional<user> payer = userRepo.findById(bill.getPayerUsername());
        if(!payer.isPresent()){
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }

        paymentRequest paymentRequest=new paymentRequest();
        paymentRequest.setUser(payer.get());
        paymentRequest.setActualAmount(bill.getTotalAmount());
        paymentRequest.setDate(LocalDate.now());
        paymentRequest.setModeOfPayment(bill.modeOfPayment);
        paymentRequest.setCategory(bill.getCategory());
        paymentRequest.setStatus("PENDING");

        for(items items:bill.getItems()){
            items.setPaymentRequest(paymentRequest);
            for(count count:items.getUserItemCount()){
                count.setItems(items);
            }
        }

        paymentRequest.setItems(bill.getItems());
        paymentRequestRepo.save(paymentRequest);
      HashMap<String,Integer> perPersonSolgery=new HashMap<>();
      //solo buying
      for(items items:bill.getItems()){
          int itemCost=items.getSingleItemAmount();

          for(count count:items.getUserItemCount()){
              String person=count.getUserName();
              int howManyItemSheBy=count.getItemCout();

              int totalCostOfUserPerItem=howManyItemSheBy*itemCost;
              if(person.equals(payer.get().getUserName())){

              }else{
              perPersonSolgery.put(person,perPersonSolgery.getOrDefault(person,0)+totalCostOfUserPerItem);
              }
          }
      }

      System.out.println(perPersonSolgery);
//      sharedByLogic
      if(bill.getSharedItems()==null){
      }else {
          paymentRequest.setSharedItems(bill.getSharedItems());
          for (sharedItems sharedItems : bill.getSharedItems()) {

              int totalAmountOfSharedItem = sharedItems.getTotalAmountOfSharedItem();
              int perPersonHasToPay = totalAmountOfSharedItem / sharedItems.getSharedUser().size();

              for (String hasToPayInContro : sharedItems.getSharedUser()) {
                  if(hasToPayInContro.equals(payer.get().getUserName())){

                  }else{

                  perPersonSolgery.put(hasToPayInContro, perPersonSolgery.getOrDefault(hasToPayInContro, 0) + perPersonHasToPay);
                  }
              }
          }

          for(sharedItems sharedItems:bill.getSharedItems()){
              sharedItems.setPaymentRequest(paymentRequest);
          }
      }





        System.out.println(perPersonSolgery+" shared");

      List<String > friends=bill.getFriends();
      //for has to pay
      for(int i=0;i<bill.getFriends().size();i++){

          if(!friends.get(i).equals(payer.get().getUserName())) {
              Optional<com.example.niggapay.entity.user> userParticipant = userRepo.findById(friends.get(i));
              if (userParticipant.isPresent()) {


                  int htpId = saveHasToPay(userParticipant.get(), perPersonSolgery.get(userParticipant.get().getUserName()), payer.get().getUserName());
                  int htrId = hasToReceive(payer.get(), perPersonSolgery.get(userParticipant.get().getUserName()), userParticipant.get().getUserName());

                  hasToPay temp1 = hasToPayRepo.findById(htpId).get();
                  hasToReceive temp2 = hasToReceiveRepo.findById(htrId).get();

                  temp1.setHtrId(htrId);
                  temp2.setHtpId(htpId);

                  hasToPayRepo.save(temp1);
                  hasToReceiveRepo.save(temp2);
              }
          }
      }

      //for suggestion
      suggestionSystemService.updateFreq(bill.category, bill.getItems());


      //for has to recieve
//      for(int i=0;i<friends.size();i++){
//          Optional<com.example.niggapay.entity.user> participant = userRepo.findById(friends.get(i));
//          if(participant.isPresent()){
//              hasToReceive(payer.get(),perPersonSolgery.get(participant.get().getUserName()),participant.get().getUserName());
//          }
//      }



      List<items> sharedItemss=new ArrayList<>();
      suggestionSystemService.updateFreq(bill.getCategory(),sharedItemss);


      System.out.println(perPersonSolgery);
      return ResponseEntity.ok("create bill");

    }


    int saveHasToPay(user user,int amount,String payer){
        hasToPay hasToPay=new hasToPay();
        hasToPay.setUser(user);
        hasToPay.setAmount(amount);
        hasToPay.setOwner(payer);
        hasToPay.setStatusOfApprovement("PENDING");
        hasToPay.setStatusOfPayment("PENDING");
        hasToPay.setDate(LocalDate.now());
        user.getHasToPays().add(hasToPay);
        hasToPayRepo.save(hasToPay);

        int id=hasToPay.getHtpId();
        System.out.println(id+"htp Id");
        return id;
    }


    int hasToReceive(user user ,int amount,String jeniPaseThiLevanaTenuName){
      hasToReceive hasToReceive=new hasToReceive();
      hasToReceive.setUser(user);
      hasToReceive.setAmount(amount);
      hasToReceive.setNameOfPersonfromWhomMoneyINeedToReciveMoney(jeniPaseThiLevanaTenuName);
      hasToReceive.setStatusOfPayment("PENDING");
      hasToReceive.setStatusOfApprovement("PENDING");
      hasToReceive.setDate(LocalDate.now());
      user.getHasToReceives().add(hasToReceive);

      hasToReceiveRepo.save(hasToReceive);

      int id=hasToReceive.getHtrId();
      System.out.println(id+"htr id");
      return id;
    }

    public ResponseEntity<?> addUser(user user) {

        Optional<user> t=userRepo.findById(user.getUserName());

        if(t.isPresent()){
            return new ResponseEntity<>("already exist", HttpStatus.CONFLICT);
        }else{

      return new ResponseEntity<>(userRepo.save(user),HttpStatus.OK);
        }


    }

    public paymentRequestResponseToSeeTheBill getBill(int billId) {

        paymentRequest d = paymentRequestRepo.findById(billId).get();
        paymentRequestResponseToSeeTheBill response=new paymentRequestResponseToSeeTheBill(
              d.getPaymentRequestId(),(d.getActualAmount()),
                d.getModeOfPayment(),
                d.getCategory(),
                d.getStatus(),
                d.getDate(),
                d.getUser(),
                d.getItems(),
                d.getSharedItems()
      );
        return response;
    }

    public List<hasToPay> getHasToPay(String userName) {
      return hasToPayRepo.getAllByUsername(userName);
    }

    public List<hasToReceive> getHasToReceive(String userName) {
      return hasToReceiveRepo.getAllByUsername(userName);
    }

    public void test1(String userName) {
        List<hasToReceive> test = hasToReceiveRepo.findByUserNative(userName);

        System.out.println("working test1");
        for(int i=0;i<test.size();i++){
            System.out.println(test.get(i).getUser().getUserName());
            System.out.println(test.get(i).getNameOfPersonfromWhomMoneyINeedToReciveMoney());
        }
    }


    public  boolean hasToReciveStatusChange(String  owner,String otherParty){
        System.out.println("working hasToReciveStatusChange");
        List<hasToReceive> byOwnerandOther = hasToReceiveRepo.findByOwnerandOther(owner, otherParty);
        for(int i=0;i<byOwnerandOther.size();i++){
            System.out.println(byOwnerandOther.get(i).getNameOfPersonfromWhomMoneyINeedToReciveMoney());
        }
        return true;
    }

    public  List<hasToReceive> getPendingHasToReceive(String  userName) {
        List<hasToReceive> hasToReceive = hasToReceiveRepo.findByownerpending(userName);
        hasToReceive.size();
        return hasToReceive;
    }


    public  List<hasToPay> getPendingHasToPay(String  userName) {
        System.out.println("working hasToReceive");
        List<hasToPay> hasToPays = hasToPayRepo.findByownerpending(userName);

        hasToPays.size();
        return hasToPays;
    }



//    @Param("sendingParty") String  sendingParty,
//    @Param("receivingParty") String  receivingParty,
//    @Param("htpId") String  htpId


    public  ResponseEntity<?> changeStatusHtp(String sending,String receiving,int htpId,String yesOrNo){

        List<hasToPay> hasToPays = hasToPayRepo.changeStatutsOfApprovementOfHtp(
                sending,
                receiving,
                htpId
        );



        if(hasToPays.size()>0){
            hasToPay first=hasToPays.get(0);

            if(yesOrNo.contains("YES")){

            first.setStatusOfApprovement("PASS");
            }else{
            first.setStatusOfApprovement("FAIL");
            }
            hasToPayRepo.save(first);

        }


        hasToPays.size();

        return  new ResponseEntity<>(hasToPays,HttpStatus.OK);


    }


    public  ResponseEntity<?> changeStatusHtp(int htpId,String yesOrNo){


        Optional<hasToPay> hasToPays = hasToPayRepo.findById(htpId);

        if(hasToPays.isPresent()){
            hasToPay first=hasToPays.get();

            if(yesOrNo.contains("YES")){

                first.setStatusOfApprovement("PASS");
            }else{
                first.setStatusOfApprovement("FAIL");
            }
            hasToPayRepo.save(first);


            //htr status
            int htrId=first.getHtrId();
            Optional<hasToReceive> htr = hasToReceiveRepo.findById(htrId);
            if(htr.isPresent()){
                hasToReceive htrObject=htr.get();

                if(yesOrNo.contains("YES")){
                    htrObject.setStatusOfApprovement("PASS");
                }else{
                    htrObject.setStatusOfApprovement("FAIL");
                }

                hasToReceiveRepo.save(htrObject);

            }


        }






        return  new ResponseEntity<>(hasToPays,HttpStatus.OK);


    }



    public  ResponseEntity<?> changeStatusHtr(int htr_id,String yesOrNo){

        Optional<hasToReceive> htr = hasToReceiveRepo.findById(htr_id);

        if(htr.isPresent()){


            hasToReceive h=htr.get();
            if(yesOrNo.contains("YES")){

            h.setStatusOfApprovement("PASS");
            changeStatusHtp(h.getHtpId(),"YES");
            }else{
            h.setStatusOfApprovement("FAIL");

            changeStatusHtp(h.getHtpId(),"FAIL");
            }
            hasToReceiveRepo.save(h);



            int htpId=h.getHtpId();
            Optional<hasToPay> htp = hasToPayRepo.findById(htpId);

            if(htp.isPresent()){
                hasToPay htpObject=htp.get();

                if(yesOrNo.contains("YES")){
                    htpObject.setStatusOfApprovement("PASS");
                }else{
                    htpObject.setStatusOfApprovement("FAIL");
                }

                hasToPayRepo.save(htpObject);
            }





            return   new ResponseEntity<>(h,HttpStatus.OK);



        }else{
            return new ResponseEntity<>("no entry found",HttpStatus.NOT_FOUND);
        }
    }




    //GET htp with approval status pass and payment status pending

    public ResponseEntity<?> getHtpApprovedAndPendingHtp(String userName){
        List<hasToPay> bystatusOfApprovementPassAndStatusOfPaymentPending = hasToPayRepo.findBystatusOfApprovementPassAndStatusOfPaymentPending(userName);

        bystatusOfApprovementPassAndStatusOfPaymentPending.size();
        return new  ResponseEntity<>(bystatusOfApprovementPassAndStatusOfPaymentPending,HttpStatus.OK);
    }

    public ResponseEntity<?> getHtpApprovedAndPendingHtr(String userName){
        List<hasToReceive> bystatusOfApprovementPassAndStatusOfPaymentPending = hasToReceiveRepo.findBystatusOfApprovementPassAndStatusOfPaymentPending(userName);

        bystatusOfApprovementPassAndStatusOfPaymentPending.size();
        return new  ResponseEntity<>(bystatusOfApprovementPassAndStatusOfPaymentPending,HttpStatus.OK);
    }





    //main approve reject payment



    public  ResponseEntity<?> sendMoney(
            int id
    ){

        Optional<hasToPay> hasToPay = hasToPayRepo.findById(id);

        if(hasToPay.isPresent()){
            hasToPay.get().setStatusOfPayment("Done By Payer Side");
            hasToPayRepo.save(hasToPay.get());
            return new ResponseEntity<>(hasToPay.get(),HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("no entry found",HttpStatus.NOT_FOUND);
        }

    }




  public   ResponseEntity<?> confirmationOfMoneyLaundry(int id){

        Optional<hasToReceive> htr=hasToReceiveRepo.findById(id);

        if(htr.isPresent()){

            int htpId=htr.get().getHtpId();

            Optional<hasToPay> htp = hasToPayRepo.findById(htpId);

            if(htp.isPresent()){

                hasToPay htpObject=htp.get();
                htpObject.setStatusOfPayment("Done By Both Side");
                hasToPayRepo.save(htpObject);

                hasToReceive htrObject=htr.get();
                htrObject.setStatusOfPayment("Done By Both Side");

                hasToReceiveRepo.save(htrObject);
                return  new ResponseEntity<>(htrObject,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("no entry found",HttpStatus.NOT_FOUND);
            }


        }else{
            return new ResponseEntity<>("no entry found",HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<?> login(String username, String password) {


        Optional<user> user = userRepo.findById(username);


        if(user.isPresent()){
            String actualPassword=user.get().getPassword();
            String actualUsername=user.get().getUserName();

            if(actualUsername.equals(username)){
                if(actualPassword.equals(password)){
                    return new ResponseEntity<>("OK",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Wrong Password",HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("Wrong UserName",HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
    }
}








//    public String payerUsername;
//    public double totalAmount;
//    public String category;
//    public List<items> items;
//    public List<String> friends;
//    public String modeOfPayment;