package com.bank.app.controller;

import com.bank.app.Model.Customer;
import com.bank.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> createCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer,@PathVariable Long id){
       try{
           return ResponseEntity.ok(customerService.updateCustomer(customer,id));
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        try{
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer deleted Successfully with id: "+id);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
