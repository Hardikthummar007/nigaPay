package com.example.niggapay.repository;

import com.example.niggapay.entity.hasToPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface hasToPayRepo extends JpaRepository<hasToPay,Integer > {
    //    @Query("SELECT h FROM hasToPay h WHERE h.user.username = :username")
//    List<hasToPay> getAllByUsername(String username);
    @Query("SELECT h FROM hasToPay h WHERE h.user.userName = :username")
    List<hasToPay> getAllByUsername(@Param("username") String username);



    @Query(value = "SELECT * FROM has_to_pay WHERE user_user_name = :owner and status_of_approvement= 'PENDING'", nativeQuery = true)
    List<hasToPay> findByownerpending(
            @Param("owner") String  owner
    );


    @Query(value = "SELECT * FROM has_to_pay WHERE user_user_name = :sendingParty and owner= :receivingParty  and htp_id= :htpId", nativeQuery = true)
    List<hasToPay> changeStatutsOfApprovementOfHtp(
            @Param("sendingParty") String  sendingParty,
            @Param("receivingParty") String  receivingParty,
            @Param("htpId") int  htpId
    );



    @Query(value = "SELECT * FROM has_to_pay WHERE status_of_approvement= 'PASS' and status_of_payment= 'PENDING' and  user_user_name= :userName", nativeQuery = true)
    List<hasToPay> findBystatusOfApprovementPassAndStatusOfPaymentPending(
            @Param("userName") String userName
    );









}
