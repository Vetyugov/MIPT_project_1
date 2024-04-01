package ru.netology.nvetyugov.finalProject.model.operations;

import lombok.Data;
import ru.netology.nvetyugov.finalProject.util.ConsolePrintable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanOperation extends Operation implements ConsolePrintable, Serializable {
    private int loanId;

    public LoanOperation(int id, BigDecimal value, LocalDateTime time, int loanId, int clientId) {
        super(id, value, time, clientId);
        this.loanId = loanId;
    }

    public void print() {
        super.print();
        System.out.println("Идентификатор кредитного счета " + loanId);
    }
}