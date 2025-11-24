package com.example.niggapay.repository;

import com.example.niggapay.entity.suggestionSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository
public interface suggestionSystemRepo extends JpaRepository<suggestionSystem,Integer> {

    suggestionSystem findByItemName(String itemName);
}
