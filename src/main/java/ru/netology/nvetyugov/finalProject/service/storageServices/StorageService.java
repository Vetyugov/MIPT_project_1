package ru.netology.nvetyugov.finalProject.service.storageServices;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.netology.nvetyugov.finalProject.model.OperationData;
import ru.netology.nvetyugov.finalProject.service.SerializationService;

import java.util.ArrayList;
import java.util.List;


/**
 * Сервис для работы с хранением данных
 */
@Service
@Scope ("prototype")
public class StorageService<T> {


    private List<T> dataList = new ArrayList<T>();


    public void addData(T data) {
        dataList.add(data);
    }

    public List<T> getAllData() {
        return dataList;
    }

    public void deleteData(T data) {
        dataList.remove(data);
    }


}
