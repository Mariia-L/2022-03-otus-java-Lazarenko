package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.ClientMessage;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc;
import ru.otus.protobuf.generated.ServerMessage;

public class RemoteDBServiceImpl extends RemoteDBServiceGrpc.RemoteDBServiceImplBase {

    @Override
    public void getValue(ClientMessage request, StreamObserver<ServerMessage> responseObserver) {

        for (long i = request.getFirstValue(); i <= request.getLastValue(); i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(serverMessage(i));
        }

        responseObserver.onCompleted();
    }

    private ServerMessage serverMessage(long value) {
        return ServerMessage.newBuilder()
                .setValue(value)
                .build();
    }
}
