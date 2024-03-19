package ru.netology.nvetyugov.task6;

import lombok.extern.slf4j.Slf4j;
import ru.netology.nvetyugov.task6.exceptions.CustomerOperationOutOfBoundException;
import ru.netology.nvetyugov.task6.exceptions.OperationOutOfBoundException;
import ru.netology.nvetyugov.task6.operations.Operation;

import java.util.Arrays;
import java.util.Optional;

public class ProjectRepository {
    private final int CUSTOMERS_SIZE = 5;
    private final int OPERATIONS_SIZE = 50;
    private final int MAX_OPERATIONS_COUNT = 1;
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
        int operationId = operation.getId();
        if (operationId > OPERATIONS_SIZE) {
          throw new OperationOutOfBoundException(operationId);
        } else {
            operations[operationId] = operation;
            addOperationToClientStatement(customerId, operation);
        }
    }


    /**
     * Добавление операции в выписку клиента
     * @param customerId
     * @param operation
     */
    public void addOperationToClientStatement(int customerId, Operation operation) {
        int[] ints = statement[customerId];
        if (ints[ints.length - 1] != 0) {
            throw new CustomerOperationOutOfBoundException(customerId,operation.getId());
        }
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
