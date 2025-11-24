package com.example.niggapay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableScheduling
@CrossOrigin(value = "*")
public class NiggapayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiggapayApplication.class, args);
	}

}