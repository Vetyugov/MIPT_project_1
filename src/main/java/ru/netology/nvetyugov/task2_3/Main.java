package ru.netology.nvetyugov.task2_3;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Transaction> list = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        printMainInfo();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "1" -> addTransaction(scanner);
                case "2" -> printList();
                case "3" -> exit();
            }
            printMainInfo();
        }
    }

    private void printMainInfo() {
        System.out.println("Выберите команду: \n" +
                "1 - Добавить транзакцию\n" +
                "2 - Просмотреть транзакции\n" +
                "3 - Выход");
    }

    private void addTransaction(Scanner scanner) {
        try {
            System.out.println("Введите id:");
            Transaction transaction = new Transaction();
            transaction.setId(Long.parseLong(scanner.nextLine()));
            System.out.println("Введите имя пользователя:");
            transaction.setUserName(scanner.nextLine());
            System.out.println("Введите значение транзакции (число с плавающей запятой):");
            transaction.setValue(BigDecimal.valueOf(Double.parseDouble(scanner.nextLine())));
            System.out.println("Введите дату транзакции (формат dd-MM-yyyy HH:mm:ss):");
            transaction.setTime(LocalDateTime.parse(scanner.nextLine(), formatter));
            list.add(transaction);
        } catch (Exception e) {
            System.out.println("Некорректный формат данных");
        }

    }

    private void printList() {
        for (Transaction transaction : list) {
            System.out.println(transaction);
        }
    }

    private void exit() {
        System.exit(1);
    }
}
