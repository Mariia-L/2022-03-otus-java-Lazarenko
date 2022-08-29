package ru.otus.protobuf;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc;
import ru.otus.protobuf.generated.ClientMessage;
import ru.otus.protobuf.generated.ServerMessage;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

public class GRPCClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws InterruptedException {
        var channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();

        var latch = new CountDownLatch(1);
        var newStub = RemoteDBServiceGrpc.newStub(channel);
        Stack<ServerMessage> serverMessageStack = new Stack<>();

        newStub.getValue(ClientMessage.newBuilder().setFirstValue(0).setLastValue(30).build(), new StreamObserver<ServerMessage>() {

            @Override
            public void onNext(ServerMessage value) {
                serverMessageStack.push(value);
                System.out.println("New value from server: " + value.getValue());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error!!");
            }

            @Override
            public void onCompleted() {
                System.out.println("Done!");
            }
        });

        long currentValue = 0;

        for (long i = 0; i <=50; i++) {
            Thread.sleep(1000);
            long serverValue = serverMessageStack.empty() ? 0 : serverMessageStack.pop().getValue();
            currentValue = currentValue + serverValue + 1;
            System.out.println("Value " + currentValue);
        }

        latch.await();

        channel.shutdown();
    }
}
