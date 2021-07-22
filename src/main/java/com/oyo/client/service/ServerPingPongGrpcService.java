package com.oyo.client.service;

import com.oyo.grpc.PingPongServiceGrpc;
import com.oyo.grpc.PingRequest;
import com.oyo.grpc.PongResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class ServerPingPongGrpcService extends PingPongServiceGrpc.PingPongServiceImplBase {
    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {
        List<Integer> randomIntegers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomIntegers.add(secureRandom.nextInt(Integer.MAX_VALUE));
        }
        PongResponse response = PongResponse.newBuilder()
                .addAllItems(randomIntegers)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    //Streaming one random number one by one is not suitable -- better example would be pagination (don't make chunks way too small else there'll be performance hit like this e.g.)
    @Override
    public void pingServStream(PingRequest request, StreamObserver<PongResponse> responseObserver) {
        for (int i = 0; i < 10; i++) {
            List<Integer> randomIntegers = new ArrayList<>();
            randomIntegers.add(secureRandom.nextInt(Integer.MAX_VALUE));
            PongResponse response = PongResponse.newBuilder()
                    .addAllItems(randomIntegers)
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }
}
