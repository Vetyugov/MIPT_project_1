package ru.netology.nvetyugov.task6;

import ru.netology.nvetyugov.task6.exceptions.CustomerOperationOutOfBoundException;
import ru.netology.nvetyugov.task6.exceptions.OperationOutOfBoundException;
import ru.netology.nvetyugov.task6.operations.CashbackOperation;
import ru.netology.nvetyugov.task6.operations.LoanOperation;
import ru.netology.nvetyugov.task6.operations.Operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private final ProjectRepository projectRepository = new ProjectRepository();

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
                case "0" -> addCustomer(scanner);
                case "1" -> addNewOperation(scanner);
                case "2" -> printOperations(scanner);
            }
            printMainInfo();
        }
    }

    private void printMainInfo() {
        System.out.println("Выберите команду: \n" +
                "0 - Добавить пользователя\n" +
                "1 - Добавить операцию\n" +
                "2 - Вывести список операция пользователя\n" +
                "3 - Выход");
    }

    /**
     * @param scanner
     * @return идентификатор добавленного пользователя
     */
    private int addCustomer(Scanner scanner) {
        System.out.println("Добавление нового пользователя");
        System.out.println("Введите id:");
        int i = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите имя пользователя:");
        String name = scanner.nextLine();
        System.out.println("Введите логин пользователя:");
        String login = scanner.nextLine();
        projectRepository.addCustomer(Customer.builder()
                .id(i)
                .name(name)
                .login(login)
                .build());
        return i;
    }

    /**
     * @param scanner
     * @return идентификатор добавленной операции
     */
    private int addNewOperation(Scanner scanner) {
        System.out.println("Добавление новой операции");
        int userId = checkUser(scanner);
        System.out.println("Введите id операции:");
        int operationId = Integer.parseInt(scanner.nextLine());
        if (operationId < 1) {
            System.out.println("Id операции должно быть больше нуля! Повторите добавление операции");
            return -1;
        }
        System.out.println("Введите сумму перевода:");
        BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        System.out.println("Какой тип операции вы хотите добавить? \n"
                + "0 - обычная операция \n"
                + "1 - операция с кэшбеком \n"
                + "2 - кредитная операция");
        String type = scanner.nextLine();
        switch (type) {
            case "0" -> addSimpleOperation(userId, operationId, sum);
            case "1" -> {
                System.out.println("Введите размер кэшбека");
                int cashback = Integer.parseInt(scanner.nextLine());
                addCashbackOperation(userId, operationId, sum, cashback);
            }
            case "2" -> {
                System.out.println("Введите id кредитного счета");
                int loanId = Integer.parseInt(scanner.nextLine());
                addLoanOperation(userId, operationId, sum, loanId);
            }
        }


        return operationId;
    }

    private void addSimpleOperation(int userId, int operationId, BigDecimal sum) {
        try {
            projectRepository.addNewOperation(userId,
                    new Operation(operationId, sum, LocalDateTime.now())
            );
        } catch (OperationOutOfBoundException | CustomerOperationOutOfBoundException e) {
            System.err.println(e.getMessage());
        }

    }


    private void addCashbackOperation(int userId, int operationId, BigDecimal sum, int cashbackAmound) {
        try {
            projectRepository.addNewOperation(userId,
                    new CashbackOperation(operationId, sum, LocalDateTime.now(), cashbackAmound));
        } catch (CustomerOperationOutOfBoundException e) {
            System.err.println(e.getMessage());
        }

    }

    private void addLoanOperation(int userId, int operationId, BigDecimal sum, int loanId) {
        try {
            projectRepository.addNewOperation(userId,
                    new LoanOperation(operationId, sum, LocalDateTime.now(), loanId));
        } catch (CustomerOperationOutOfBoundException e) {
            System.err.println(e.getMessage());
        }

    }


    /**
     * проверяет существование пользователя для добавления операции
     *
     * @param scanner
     * @return
     */
    private int checkUser(Scanner scanner) {
        System.out.println("Введите id пользователя:");
        int i = Integer.parseInt(scanner.nextLine());
        if (projectRepository.findCustomer(i).isEmpty()) {
            System.out.println("Пользователь не найден! Введите новое id или создайте пользователя. Добавить нового пользователя - 0. Ввести id заново - 1");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                i = addCustomer(scanner);
            }
            if (choice == 1) {
                checkUser(scanner);
            }
        }
        return i;

    }

    private void printOperations(Scanner scanner) {
        int customerId = checkUser(scanner);
        for (Operation op : projectRepository.getOperations(customerId)) {
            op.print();
        }
    }

}
