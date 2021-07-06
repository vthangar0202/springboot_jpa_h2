package com.vasanth.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vasanth.spring.model.Customers;
import com.vasanth.spring.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customers>> getAllCustomers(@RequestParam(required = false) String name) {
        try {
            List<Customers> customers = new ArrayList<Customers>();
            if (name == null) {
                customerRepository.findAll().forEach(customers::add);
            } else {
                customerRepository.findByName(name).forEach(customers::add);
            }

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<List<Customers>> getCustomerById(@PathVariable("id") long id) {

        Optional<Customers> customersData = customerRepository.findById(id);
        if (customersData.isPresent()) {
            return new ResponseEntity(customersData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Customers> AddCustomer(@RequestBody Customers customer) {
        try {
            Customers _customer = customerRepository.save(new Customers(customer.getName(), customer.getAge(),
                    customer.getEmail(), customer.getMobileNumber()));
            return new ResponseEntity<>(_customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable("id") long id, @RequestBody Customers customer) {

        Optional<Customers> customerData = customerRepository.findById(id);

        if (customerData.isPresent()) {
            Customers _customer = customerData.get();
            _customer.setName(customer.getName());
            _customer.setAge(customer.getAge());
            _customer.setEmail(customer.getEmail());
            _customer.setMobileNumber(customer.getMobileNumber());

            return new ResponseEntity<>(customerRepository.save(_customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customers> deleteCustomer(@PathVariable("id") long id) {
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
