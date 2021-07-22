package com.oyo.client.service;

import com.oyo.client.PingPongStreamingResponse;
import com.oyo.grpc.PingPongServiceGrpc;
import com.oyo.grpc.PingRequest;
import com.oyo.grpc.PongResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ClientPingPongGrpcService {
    @GrpcClient("PingPongService") //proto file name is considered as interceptor's name OR may be not lol
    PingPongServiceGrpc.PingPongServiceStub nonBlockStub;

    @GrpcClient("PingPongService")
    PingPongServiceGrpc.PingPongServiceBlockingStub stub;

    public Object ping() {
        List<Integer> response = new ArrayList<>();
        //Overall avg GRPC's performance will be less or almost equal to rest in case there are less number of io calls (for e.g. 10 instead of 1000 here)
        for (int i = 0; i < 1000; i++) {
            PongResponse immediateResponse = stub.ping(PingRequest.newBuilder()
                    .setPing("")
                    .build());
            response.addAll(immediateResponse.getItemsList());
        }
        return response;
    }

    //Needs better streaming implementation
    public Object pingStream() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Integer>> response = new CompletableFuture<>();
        PingPongStreamingResponse streamingResponse = new PingPongStreamingResponse(new ArrayList<>(), response);
        for (int i = 0; i < 1000; i++) {
            nonBlockStub.pingServStream(PingRequest.newBuilder()
                    .setPing("")
                    .build(), streamingResponse);
        }
        streamingResponse.onCompleted();
        return response.get();
    }
}
