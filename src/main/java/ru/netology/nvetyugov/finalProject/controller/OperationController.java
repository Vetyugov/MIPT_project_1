package ru.netology.nvetyugov.finalProject.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.nvetyugov.finalProject.model.Customer;
import ru.netology.nvetyugov.finalProject.model.CustomersGetResponse;
import ru.netology.nvetyugov.finalProject.model.dto.CustomerDTO;
import ru.netology.nvetyugov.finalProject.model.dto.OperationDTO;
import ru.netology.nvetyugov.finalProject.model.operations.Operation;
import ru.netology.nvetyugov.finalProject.service.OperationService;
import ru.netology.nvetyugov.finalProject.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "operations")
public class OperationController {

    private final OperationService operationService;

    private final StatementService statementService;

    public OperationController(OperationService operationService, StatementService statementService) {
        this.operationService = operationService;
        this.statementService = statementService;
    }

    /**
     * просмотр всех операций клиента
     */
    @GetMapping ("{id}")
    private List <OperationDTO> getClientOperations (@PathVariable("id") int clientId) {
        return statementService.getOperations(clientId).stream().map(operation -> {
            return new OperationDTO(operation.getId(),operation.getValue(),operation.getTime());
        }).collect(Collectors.toList());
    }


    /**
     * добавление операции
     */
    @PutMapping
    public OperationDTO addNewOperation (@RequestParam int clientId, @RequestBody OperationDTO operationDTO) {
        Operation operation = new Operation(0,
                operationDTO.getValue(),
                operationDTO.getTime(),
                clientId);
        return operationService.addNewOperation(operation);
    }

    /**
     * удаление операции по ID
     */
    @DeleteMapping
    public void deleteOperation (@RequestBody String operationId) {
        operationService.deleteOperation(Integer.parseInt(operationId));
    }
}
