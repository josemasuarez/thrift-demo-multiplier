package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class MultiplicationServer {
    public static MultiplicationHandler multiplicationHandler;
    public static MultiplicationService.Processor processor;

    public static void main(String[] args) {
        try{
            multiplicationHandler = new MultiplicationHandler();
            processor = new MultiplicationService.Processor(multiplicationHandler);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> simpleThreadServer(processor));
            executorService.shutdown();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void simpleThreadServer(MultiplicationService.Processor processor){
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Listening new connections...");
            server.serve();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
