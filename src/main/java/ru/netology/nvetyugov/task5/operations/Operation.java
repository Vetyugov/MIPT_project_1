package ru.netology.nvetyugov.task5.operations;

import lombok.*;
import ru.netology.nvetyugov.task5.ConsolePrintable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements ConsolePrintable {
    private int id;
    private BigDecimal value;
    private LocalDateTime time;

    public void print(){
        System.out.println("Сумма операции : " + value + "; Время проведения операции: " + time);
    }
}
