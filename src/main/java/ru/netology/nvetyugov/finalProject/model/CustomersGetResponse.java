package ru.netology.nvetyugov.finalProject.model;

import lombok.Data;
import ru.netology.nvetyugov.finalProject.model.dto.CustomerDTO;

import java.util.List;

@Data
public class CustomersGetResponse {
    private final List<CustomerDTO> customers;
}


