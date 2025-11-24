package com.example.niggapay.repository;

import com.example.niggapay.entity.hasToPay;
import com.example.niggapay.entity.membersOfGroupBike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface membersOfGroupRepo extends JpaRepository<membersOfGroupBike,Integer> {


    @Query(value = "SELECT * FROM members_of_group_bike WHERE bike_group_group_name= :groupId and name= :userName", nativeQuery = true)
    membersOfGroupBike findByUsernameGroupid(
            @Param("groupId") String groupId,
            @Param("userName") String userName
    );




}
