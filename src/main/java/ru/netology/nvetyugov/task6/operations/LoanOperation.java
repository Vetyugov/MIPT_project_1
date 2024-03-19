package ru.netology.nvetyugov.task6.operations;

import lombok.Data;
import ru.netology.nvetyugov.task6.ConsolePrintable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    public LoanOperation(int id, BigDecimal value, LocalDateTime time, int loanId) {
        super(id, value, time);
        this.loanId = loanId;
    }

    public void print() {
        super.print();
        System.out.println("Идентификатор кредитного счета " + loanId);
    }
}