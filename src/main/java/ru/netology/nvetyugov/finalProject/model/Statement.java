package ru.netology.nvetyugov.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statement {

    private int customerId;

    private int operationId;
}
