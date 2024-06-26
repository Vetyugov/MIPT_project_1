package ru.netology.nvetyugov.finalProject.exceptions;

public class OperationOutOfBoundException extends OperationRuntimeException {
    private final String MESSAGE = "Exception while trying to save operation %s. The permissible number of operations has been exceeded";


    private int operationId;

    public OperationOutOfBoundException(int operationId) {
        super();
        this.operationId = operationId;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(operationId);
    }
}
