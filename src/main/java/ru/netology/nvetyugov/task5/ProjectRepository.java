package ru.netology.nvetyugov.task5;

import ru.netology.nvetyugov.task5.operations.Operation;

import java.util.Arrays;
import java.util.Optional;

public class ProjectRepository {
    private final int CUSTOMERS_SIZE = 5;
    private final int OPERATIONS_SIZE = 20;
    private final int MAX_OPERATIONS_COUNT = 20;
    private final Operation[] operations = new Operation[OPERATIONS_SIZE];
    private final Customer[] customers = new Customer[CUSTOMERS_SIZE];
    /**
     * statement[{id клиента из массива Customer[] customers}][{порядковый номер в разрезе клиента}] = id операции
     * из массива Operation[] operations ;
     */
    private final int[][] statement = new int[CUSTOMERS_SIZE][MAX_OPERATIONS_COUNT];

    public void addCustomer(Customer customer) {
        customers[customer.getId()] = customer;
    }

    public Optional<Customer> findCustomer(int customerId) {
        if (customerId > CUSTOMERS_SIZE) return Optional.empty();
        return Optional.ofNullable(customers[customerId]);
    }

    public void addNewOperation(int customerId, Operation operation) {
        operations[operation.getId()] = operation;
        addOperationToClient(customerId, operation);
    }

    private void addOperationToClient(int customerId, Operation operation) {
        int[] ints = statement[customerId];
        for (int i = 0; i < MAX_OPERATIONS_COUNT; i++) {
            if (ints[i] == 0) {
                ints[i] = operation.getId();
                return;
            }
        }
    }

    public Operation[] getOperations(int clientId) {
        Operation[] clientOperations = new Operation[MAX_OPERATIONS_COUNT];
        int[] ints = statement[clientId];
        int operationCount = 0;
        for (int i = 0; i < MAX_OPERATIONS_COUNT; i++) {
            if (ints[i] != 0) {
                clientOperations[i] = operations[ints[i]];
                operationCount++;
            }

        }
        return Arrays.copyOf(clientOperations, operationCount);

    }
}
