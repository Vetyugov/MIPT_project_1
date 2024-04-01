package ru.netology.nvetyugov.finalProject.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.nvetyugov.finalProject.model.Customer;
import ru.netology.nvetyugov.finalProject.model.CustomersGetResponse;
import ru.netology.nvetyugov.finalProject.model.dto.CustomerDTO;
import ru.netology.nvetyugov.finalProject.service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomersGetResponse getCustomers(){
        List<Customer> customers = customerService.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id){
        Optional <Customer> customerOpt = customerService.findCustomer(id);
        return customerOpt.map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .orElse(new CustomerDTO(-1, "Customer not found"));
    }

    @PostMapping
    public CustomerDTO addNewCustomer (@RequestParam String name, @RequestParam String login) {
        Customer customer = customerService.addCustomer(name,login);
        return new CustomerDTO(customer.getId(), customer.getName());
    }

    @PutMapping
    public CustomerDTO updateCustomer (@RequestBody CustomerDTO customer) {
        Optional <Customer> customerOpt = customerService.findCustomer(customer.getId());
        if (customerOpt.isPresent()) {
            Customer updatedCustomer = customerOpt.get();
            updatedCustomer.setName(customer.getName());
            return customer;
        } else return new CustomerDTO(-1, "Customer not found");

    }

    @DeleteMapping
    public void deleteCustomer (@RequestBody String customerId) {
        customerService.deleteCustomer(Integer.parseInt(customerId));
    }
}
