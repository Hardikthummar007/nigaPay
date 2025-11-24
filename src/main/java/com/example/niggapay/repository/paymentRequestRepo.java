package com.example.niggapay.repository;

import com.example.niggapay.entity.paymentRequest;
import com.example.niggapay.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface paymentRequestRepo extends JpaRepository<paymentRequest,Integer> {

}
