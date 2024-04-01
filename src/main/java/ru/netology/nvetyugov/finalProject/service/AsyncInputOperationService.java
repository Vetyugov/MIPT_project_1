package ru.netology.nvetyugov.finalProject.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.nvetyugov.finalProject.configuration.OperationProperties;
import ru.netology.nvetyugov.finalProject.model.operations.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Service
public class AsyncInputOperationService {

    private Queue<Operation> queue = new LinkedList<>();

    private final OperationService operationService;

    private final OperationProperties operationProperties;

    public AsyncInputOperationService(OperationService operationService, OperationProperties operationProperties) {
        this.operationService = operationService;
        this.operationProperties = operationProperties;
    }

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    @PostConstruct
    public void init(){
        this.startAsyncOperationProcessing();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    //log.info("Waiting for next operation in queue");
                    Thread.sleep(operationProperties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                log.info("Processing operation:” + operation");
                operationService.addNewOperation(operation);
            }
        }
    }


    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }



}
