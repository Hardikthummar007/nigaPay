package com.example.niggapay.repository;

import com.example.niggapay.entity.hasToPay;
import com.example.niggapay.entity.hasToReceive;
import com.example.niggapay.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ValueExpressionDelegate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface hasToReceiveRepo extends JpaRepository<hasToReceive,Integer > {

    @Query("SELECT h FROM hasToReceive h WHERE h.user.userName = :username")
    List<hasToReceive> getAllByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM has_to_receive WHERE user_user_name = :username", nativeQuery = true)
    List<hasToReceive> findByUserNative(@Param("username") String username);

    @Query(value = "SELECT * FROM has_to_receive WHERE user_user_name = :owner and name_of_personfrom_whom_moneyineed_to_recive_money=:other", nativeQuery = true)
    List<hasToReceive> findByOwnerandOther(
            @Param("owner") String username,
            @Param("other") String other
    );

    @Query(value = "SELECT * FROM has_to_receive WHERE user_user_name = :owner and status_of_approvement= 'PENDING'", nativeQuery = true)
    List<hasToReceive> findByownerpending(
            @Param("owner") String  owner
    );


    @Query(value = "SELECT * FROM has_to_receive WHERE status_of_approvement= 'PASS' and status_of_payment= 'PENDING' and user_user_name= :userName", nativeQuery = true)
    List<hasToReceive> findBystatusOfApprovementPassAndStatusOfPaymentPending(
            @Param("userName") String userName
    );

}
