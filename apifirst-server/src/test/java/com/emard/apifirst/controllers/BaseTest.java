package com.emard.apifirst.controllers;

import com.emard.apifirst.repositories.CustomerRepository;
import com.emard.apifirst.repositories.OrderRepository;
import com.emard.apifirst.repositories.ProductRepository;
import ord.emard.apifirst.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class BaseTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    WebApplicationContext wac;
    public MockMvc mockMvc;
    Customer testCustomer;
    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        testCustomer = customerRepository.findAll().iterator().next();
    }
}
