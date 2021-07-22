package com.oyo.client.controller;

import com.oyo.client.service.ServerPingPongRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest")
public class ServerRestApiController {
    @Autowired
    ServerPingPongRestService restService;

    @RequestMapping("/randomGen")
    public List<Integer> pingRandomIntegers(){
        return restService.randomGen();
    }
}
