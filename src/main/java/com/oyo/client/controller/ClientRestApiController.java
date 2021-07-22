package com.oyo.client.controller;

import com.oyo.client.service.ClientPingPongRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class ClientRestApiController {
    @Autowired
    ClientPingPongRestService restService;

    @GetMapping("/ping")
    public Object pingRest() {
        return restService.pingRest();
    }
}
