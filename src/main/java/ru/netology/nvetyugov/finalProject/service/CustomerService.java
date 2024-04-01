package ru.netology.nvetyugov.finalProject.service;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.netology.nvetyugov.finalProject.model.Customer;
import ru.netology.nvetyugov.finalProject.model.operations.Operation;
import ru.netology.nvetyugov.finalProject.service.storageServices.StorageService;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с клиентами
 */

@Service
public class CustomerService {

    private final StorageService<Customer> customerStorage;

    public CustomerService(StorageService<Customer> customerStorage) {
        this.customerStorage = customerStorage;
    }

    @PostConstruct
    public void initStorage() {
        customerStorage.addData(new Customer(1, "Spring", "spring"));
        customerStorage.addData(new Customer(2, "Boot", "boot"));

    }

    public Customer addCustomer(String name, String login) {
        int maxId = findAll().stream()
                .map(Customer::getId)
                .max(Integer::compare).orElse(0);
        Customer customer = new Customer(maxId + 1, name, login);
        customerStorage.addData(customer);
        return customer;
    }

    public void addAll (List <Customer> customers) {
        customers.forEach(customer -> customerStorage.addData(customer));
    }

    public Optional<Customer> findCustomer(int customerId) {
        return customerStorage.getAllData().stream().filter(c -> c.getId() == customerId).findFirst();
    }

    public List<Customer> findAll() {
        return customerStorage.getAllData();
    }

    public void deleteCustomer(int id) {
        if (findCustomer(id).isPresent()) {
            customerStorage.deleteData(findCustomer(id).get());
        }

    }


}
