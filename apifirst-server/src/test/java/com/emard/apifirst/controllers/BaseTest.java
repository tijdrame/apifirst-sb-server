package com.emard.apifirst.controllers;

import com.emard.apifirst.repositories.CustomerRepository;
import com.emard.apifirst.repositories.OrderRepository;
import com.emard.apifirst.repositories.ProductRepository;
import jakarta.servlet.Filter;
import ord.emard.apifirst.model.Customer;
import ord.emard.apifirst.model.Order;
import ord.emard.apifirst.model.Product;
import org.apache.commons.codec.binary.Base64;
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
    @Autowired
    Filter validationFilter;
    public MockMvc mockMvc;
    Customer testCustomer;
    Product testProduct;
    Order testOrder;
    String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("<username>:<password>").getBytes()));
    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(validationFilter)
                .build();
        testCustomer = customerRepository.findAll().iterator().next();
        testProduct = productRepository.findAll().iterator().next();
        testOrder = orderRepository.findAll().iterator().next();
    }
}
