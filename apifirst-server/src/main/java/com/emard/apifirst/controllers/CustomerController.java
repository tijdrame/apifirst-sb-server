package com.emard.apifirst.controllers;

import com.emard.apifirst.services.CustomerService;
import lombok.RequiredArgsConstructor;
import ord.emard.apifirst.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.emard.apifirst.controllers.CustomerController.BASE_URL;


/**
 * Created by jt, Spring Framework Guru.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/v1/customers";

    private final CustomerService customerService;

    @PostMapping
    ResponseEntity<Void> saveNewCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        return ResponseEntity.created(URI.create(BASE_URL + "/" + savedCustomer.getId())).build();
    }
    @GetMapping
    public ResponseEntity<List<Customer>> listCustomers(){
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") UUID customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

}
