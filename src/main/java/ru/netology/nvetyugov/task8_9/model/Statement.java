package ru.netology.nvetyugov.task8_9.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statement {

    private int customerId;

    private int operationId;
}
