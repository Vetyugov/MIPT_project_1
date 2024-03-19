package ru.netology.nvetyugov.task7;

import lombok.Getter;
import lombok.Setter;
import ru.netology.nvetyugov.task7.exceptions.CustomerOperationOutOfBoundException;
import ru.netology.nvetyugov.task7.exceptions.OperationOutOfBoundException;
import ru.netology.nvetyugov.task7.model.Customer;
import ru.netology.nvetyugov.task7.model.operations.Operation;

import java.util.Arrays;
import java.util.Optional;

public class ProjectRepository {

    @Getter
    @Setter
    private OperationData operationData = new OperationData();

    public void addCustomer(Customer customer) {
        operationData.getCustomers()[customer.getId()] = customer;
    }

    public Optional<Customer> findCustomer(int customerId) {
        if (customerId > OperationData.CUSTOMERS_SIZE) return Optional.empty();
        return Optional.ofNullable(operationData.getCustomers()[customerId]);
    }

    public void addNewOperation(int customerId, Operation operation) {
        int operationId = operation.getId();
        if (operationId > OperationData.OPERATIONS_SIZE) {
          throw new OperationOutOfBoundException(operationId);
        } else {
            operationData.getOperations()[operationId] = operation;
            addOperationToClientStatement(customerId, operation);
        }
    }


    /**
     * Добавление операции в выписку клиента
     * @param customerId
     * @param operation
     */
    public void addOperationToClientStatement(int customerId, Operation operation) {
        int[] ints = operationData.getStatement()[customerId];
        if (ints[ints.length - 1] != 0) {
            throw new CustomerOperationOutOfBoundException(customerId,operation.getId());
        }
        for (int i = 0; i < OperationData.MAX_OPERATIONS_COUNT; i++) {
            if (ints[i] == 0) {
                ints[i] = operation.getId();
                return;
            }
        }
    }

    public Operation[] getOperations(int clientId) {
        Operation[] clientOperations = new Operation[OperationData.MAX_OPERATIONS_COUNT];
        int[] ints = operationData.getStatement()[clientId];
        int operationCount = 0;
        for (int i = 0; i < OperationData.MAX_OPERATIONS_COUNT; i++) {
            if (ints[i] != 0) {
                clientOperations[i] = operationData.getOperations()[ints[i]];
                operationCount++;
            }

        }
        return Arrays.copyOf(clientOperations, operationCount);
    }
}
