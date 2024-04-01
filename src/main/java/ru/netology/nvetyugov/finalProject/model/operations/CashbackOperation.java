package ru.netology.nvetyugov.finalProject.model.operations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.nvetyugov.finalProject.util.ConsolePrintable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbackOperation extends Operation implements ConsolePrintable, Serializable {
    private int cashbackAmount;

    public CashbackOperation(int id, BigDecimal value, LocalDateTime time, int cashbackAmount, int clientId) {
        super(id, value, time, clientId);
        this.cashbackAmount = cashbackAmount;
    }

    public void print() {
        super.print();
        System.out.println("Размер кэшбека: " + cashbackAmount);
    }
}