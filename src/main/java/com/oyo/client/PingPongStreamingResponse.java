package com.oyo.client;

import com.oyo.grpc.PongResponse;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PingPongStreamingResponse implements StreamObserver<PongResponse> {
    private final List <Integer> response;
    private final CompletableFuture<List<Integer>> futureResponse;

    public PingPongStreamingResponse(List<Integer> response, CompletableFuture<List<Integer>> futureResponse) {
        this.response = response;
        this.futureResponse = futureResponse;
    }

    @Override
    public void onNext(PongResponse pongResponse) {
        this.response.addAll(pongResponse.getItemsList());
    }

    @Override
    public void onError(Throwable throwable) {
        futureResponse.cancel(true);
    }

    @Override
    public void onCompleted() {
        futureResponse.complete(this.response);
    }
}
