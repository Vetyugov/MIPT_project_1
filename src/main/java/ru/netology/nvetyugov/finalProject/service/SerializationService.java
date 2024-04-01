package ru.netology.nvetyugov.finalProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.nvetyugov.finalProject.exceptions.ReadDataFromFileException;
import ru.netology.nvetyugov.finalProject.exceptions.WriteDataToFileException;
import ru.netology.nvetyugov.finalProject.model.OperationData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@Slf4j
public class SerializationService {

    public OperationData readDataFromFile (String path) {
        log.info("Reading data from file...");
        Path file = Paths.get(path);
        try (InputStream inputStream = Files.newInputStream(file)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (OperationData) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ReadDataFromFileException(path);
        }

    }

    public void  writeDataToFile (String path, OperationData operationData) {
        log.info("Writing data to file...");
        Path file = Paths.get(path);

        try (OutputStream outputStream = Files.newOutputStream(file)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(operationData);

        } catch (IOException e) {
            throw new WriteDataToFileException(path);
        }
    }

}
