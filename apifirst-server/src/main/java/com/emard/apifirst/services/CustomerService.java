package com.emard.apifirst.services;

import ord.emard.apifirst.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();

    Customer getCustomerById(UUID customerId);
}
