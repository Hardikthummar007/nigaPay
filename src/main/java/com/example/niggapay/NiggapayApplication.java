package com.example.niggapay;

import com.example.niggapay.controller.toLiveApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@CrossOrigin(value = "*")
public class NiggapayApplication {

    @Autowired
    toLiveApp toLiveApp;

    @Autowired
    RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(NiggapayApplication.class, args);
	}


    @Scheduled(cron = "0 */5 * * * *")
    public void scheduled(){

        toLiveApp.live();
        String url="https://nigapay-5.onrender.com/live/test";
        String s=restTemplate.getForObject(url,String.class);
        System.out.println(s);
    }



}