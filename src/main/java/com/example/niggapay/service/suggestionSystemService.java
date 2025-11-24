package com.example.niggapay.service;

import com.example.niggapay.entity.items;
import com.example.niggapay.entity.suggestionSystem;
import com.example.niggapay.repository.suggestionSystemRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class suggestionSystemService {

    @Autowired
    suggestionSystemRepo suggestionSystemRepo;
    Map<String,Map<String ,Integer>> cache=new ConcurrentHashMap<>();


    @PostConstruct
    void fillTheCache(){

        List<suggestionSystem> list=suggestionSystemRepo.findAll();
        for(suggestionSystem system:list){
            String category=system.getCategory();
            String itemName=system.getItemName();
            int freq=system.getFrequency();

            Map<String, Integer> itemMap = cache.get(category);
            if(itemMap==null){
                itemMap=new ConcurrentHashMap<>();
                cache.put(category,itemMap);
            }
            itemMap.put(itemName,freq);

        }
    }

    public  void updateFreq(String  category,List<items> item){
        Map<String, Integer> itemMap = cache.get(category);
        if (itemMap == null) {
            itemMap = new ConcurrentHashMap<>();
            cache.put(category, itemMap);
        }
        for(items items:item ){
            if(itemMap.containsKey(items.getItemName())){
                itemMap.put(items.getItemName(),itemMap.get(items.getItemName())+1);
            }else{
                itemMap.put(items.getItemName(),1);
            }
        }
    }


    public List<String> getTop5FrequentlyUsedItem(String  category){
        Map<String,Integer> itemMap=cache.get(category);

        if(itemMap == null) {
            List<String > response=new ArrayList<>();
            response.add("no item added Yet in this category");
            return response;
        }
        return itemMap.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    @PreDestroy
    void sycWithDb(){
        for(Map.Entry<String ,Map<String ,Integer>> categoryWise:cache.entrySet()){

            Map<String,Integer> itemMap=categoryWise.getValue();
            for(Map.Entry<String,Integer> itemWise:itemMap.entrySet()){
                suggestionSystem existing=suggestionSystemRepo.findByItemName(itemWise.getKey());
                if(existing!=null){
                    existing.setFrequency(itemWise.getValue());
                    suggestionSystemRepo.save(existing);
                }else{
                    existing=new suggestionSystem();
                    existing.setCategory(categoryWise.getKey());
                    existing.setFrequency(itemWise.getValue());
                    existing.setItemName(itemWise.getKey());
                    suggestionSystemRepo.save(existing);
                }
            }
        }
        System.out.println("💾 Category-wise cache synced to DB before shutdown");
    }


    @Scheduled(cron = "0 */10 * * * *")
    void timelyStoreTheDataToDb(){
        System.out.println("\uD83D\uDD52 Scheduled cache sync started...\n");

        sycWithDb();
        System.out.println("✅ Cache synced to DB\n");

    }







}


