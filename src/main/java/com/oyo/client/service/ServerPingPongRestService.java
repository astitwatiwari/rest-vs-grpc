package com.oyo.client.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServerPingPongRestService {
    public List<Integer> randomGen() {
        SecureRandom secureRandom = new SecureRandom();
        List<Integer> randomIntegers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomIntegers.add(secureRandom.nextInt(Integer.MAX_VALUE));
        }
        return randomIntegers;
    }
}
