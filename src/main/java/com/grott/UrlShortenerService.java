package com.grott;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ejb.Singleton;

@Singleton
public class UrlShortenerService {
    private Map<String,String> urlMap = new HashMap<>();

    public void removeShortUrl(String key){
        urlMap.remove(key);
    }

    public String addShortUrl(String key, String url){
        if(urlMap.containsKey(key)){
            throw new RuntimeException("Shorturl for key: "+key+" already exists");
        }
        urlMap.put(key, url);
        return key;
    }
    public String addShortUrl(String url){
        String key = generateKey();
        urlMap.putIfAbsent(key,url);
        return key;
    }

    public String generateKey(){
        Random rd = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<8;i++){
            sb.append(abc.charAt(rd.nextInt(abc.length())));
        }
        String retval = sb.toString();
        if(urlMap.containsKey(retval)){
            retval = generateKey();
        }
        return retval;
    }
    public String getUrl(String key){
        return urlMap.getOrDefault(key,null);
    }
}