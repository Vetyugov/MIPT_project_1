package ru.netology.nvetyugov.task5;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String login;
}
