package ru.netology.nvetyugov.finalProject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    private  int id;
    private  String name;
}
