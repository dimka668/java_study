package com.klyshov.baeldung.grpc.server;

import com.klyshov.baeldung.grpc.HelloRequest;
import com.klyshov.baeldung.grpc.HelloResponse;
import com.klyshov.baeldung.grpc.HelloServiceGrpc.HelloServiceImplBase;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceImplBase {

    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);

        String greeting = new StringBuilder().append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}