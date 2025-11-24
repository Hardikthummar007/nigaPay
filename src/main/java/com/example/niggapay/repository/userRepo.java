package com.example.niggapay.repository;

import com.example.niggapay.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<user,String > {

}
