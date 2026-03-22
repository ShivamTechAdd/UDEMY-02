package com.bank.app.service;

import com.bank.app.Model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }


    public Customer updateCustomer(Customer updatedCustomer,Long id){
        return customerRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(updatedCustomer.getFirstName());
                    existing.setLastName(updatedCustomer.getLastName());
                    existing.setEmail(updatedCustomer.getEmail());
                    existing.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    return customerRepository.save(existing);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not found with id :"+id));
    }

    public void deleteCustomer(Long id){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer Not Found with id :"+id);
        }
        customerRepository.deleteById(id);
    }
}
