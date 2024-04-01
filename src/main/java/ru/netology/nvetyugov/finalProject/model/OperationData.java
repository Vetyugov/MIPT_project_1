package ru.netology.nvetyugov.finalProject.model;

import lombok.Getter;
import lombok.Setter;
import ru.netology.nvetyugov.finalProject.model.Customer;
import ru.netology.nvetyugov.finalProject.model.operations.Operation;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class OperationData implements Serializable {

    private List<Operation> operations = new ArrayList <>();
    private List <Customer> customers = new ArrayList<>();
    private Map<Integer, List <Operation>> statements = new HashMap<>();


    @Override
    public String toString() {
        return "OperationData{" +
                "statement=" + statements.toString() +
                '}';
    }
}
