package ru.netology.nvetyugov.task_4;

import ru.netology.nvetyugov.task2_3.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private final int ARRAY_SIZE = 5;
    private final long[] ids = new long[ARRAY_SIZE];
    private final String[] userNames = new String[ARRAY_SIZE];
    private final BigDecimal[] values = new BigDecimal[ARRAY_SIZE];
    private final LocalDateTime[] times = new LocalDateTime[ARRAY_SIZE];
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

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
                case "1" -> addTransactions(scanner);
                case "2" -> printList();
                case "3" -> exit();
            }
            printMainInfo();
        }
    }

    private void printMainInfo() {
        System.out.println("Выберите команду: \n" +
                "1 - Добавить 5 транзакций\n" +
                "2 - Просмотреть транзакции\n" +
                "3 - Выход");
    }

    private void addTransactions(Scanner scanner) {
        try {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                System.out.println("Транзакция # " + (i+1));
                System.out.println("Введите id:");
                ids[i] = Long.parseLong(scanner.nextLine());
                System.out.println("Введите имя пользователя:");
                userNames[i] = scanner.nextLine();
                System.out.println("Введите значение транзакции (число с плавающей запятой):");
                values[i] = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
                System.out.println("Введите дату транзакции (формат dd-MM-yyyy HH:mm:ss):");
                times[i] = (LocalDateTime.parse(scanner.nextLine(), formatter));
            }
        } catch (Exception e) {
            System.out.println("Некорректный формат данных");
        }

    }

    private void printList() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            System.out.println(getStringTransaction(i));

        }

    }

    private String getStringTransaction(int number){
        return "Transaction{" +
                "id=" + ids[number] +
                ", value=" + values[number] +
                ", userName='" + userNames[number] + '\'' +
                ", time=" + times[number] +
                '}';
    }

    private void exit() {
        System.exit(1);
    }
}
