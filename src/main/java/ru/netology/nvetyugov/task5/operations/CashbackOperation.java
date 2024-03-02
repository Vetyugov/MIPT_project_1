package ru.netology.nvetyugov.task5.operations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.nvetyugov.task5.ConsolePrintable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    public CashbackOperation(int id, BigDecimal value, LocalDateTime time, int cashbackAmount) {
        super(id, value, time);
        this.cashbackAmount = cashbackAmount;
    }

    public void print() {
        super.print();
        System.out.println("Размер кэшбека: " + cashbackAmount);
    }
}