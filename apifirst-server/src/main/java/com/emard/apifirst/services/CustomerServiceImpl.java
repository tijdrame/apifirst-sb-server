package com.emard.apifirst.services;

import com.emard.apifirst.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import ord.emard.apifirst.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer saveNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public List<Customer> listCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .toList();
    }
    @Override
    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId).orElseThrow();
    }
}
