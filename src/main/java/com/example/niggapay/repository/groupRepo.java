package com.example.niggapay.repository;


import com.example.niggapay.entity.bikeGroup;
import com.example.niggapay.entity.hasToPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface groupRepo extends JpaRepository<bikeGroup,String > {

    @Query(value = "SELECT * FROM  bike_group WHERE owner_of_group = :owner", nativeQuery = true)
    List<bikeGroup> findByOwnerOfGroup(
            @Param("owner") String  owner
    );




}
