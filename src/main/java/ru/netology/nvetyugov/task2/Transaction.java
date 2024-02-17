package ru.netology.nvetyugov.task2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private String userName;
    private BigDecimal value;
    private LocalDateTime time;


    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", value=" + value +
                ", userName='" + userName + '\'' +
                ", time=" + time +
                '}';
    }
}
