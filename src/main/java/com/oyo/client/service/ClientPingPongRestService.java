package com.oyo.client.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientPingPongRestService {
    public Object pingRest(){
        final String uri = "http://localhost:8080/rest/randomGen";
        RestTemplate restTemplate = new RestTemplate();
        List<Integer> response = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            List<Integer> listObject = restTemplate.getForObject(uri, List.class);
            if (listObject != null) {
                response.addAll(listObject);
            }
        }
        return response;
    }
}
