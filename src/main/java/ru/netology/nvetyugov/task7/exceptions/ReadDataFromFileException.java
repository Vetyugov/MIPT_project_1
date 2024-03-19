package ru.netology.nvetyugov.task7.exceptions;

public class ReadDataFromFileException extends OperationRuntimeException {

    private final String MESSAGE = "Exception while trying to read data from file with path %s ";

    private String path;

    public ReadDataFromFileException(String path) {
        super();
        this.path = path;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(path);
    }
}
