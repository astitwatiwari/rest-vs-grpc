package com.oyo.client.controller;

import com.oyo.client.service.ClientPingPongGrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("grpc")
public class ClientGrpcApiController {
    @Autowired
    private ClientPingPongGrpcService grpcService;

    @GetMapping("/ping")
    public Object ping() {
        return grpcService.ping();
    }

    @GetMapping("/ping/stream")
    public Object pingStream() throws ExecutionException, InterruptedException {
        return grpcService.pingStream();
    }
}
