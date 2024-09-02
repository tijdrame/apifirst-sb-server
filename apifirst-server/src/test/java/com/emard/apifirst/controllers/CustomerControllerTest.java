package com.emard.apifirst.controllers;

import ord.emard.apifirst.model.Address;
import ord.emard.apifirst.model.Customer;
import ord.emard.apifirst.model.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static com.emard.apifirst.controllers.CustomerController.BASE_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomerControllerTest extends BaseTest {
    @DisplayName("Test Create Customer")
    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = Customer.builder()
                .name(Name.builder()
                        .lastName("Doe")
                        .firstName("John")
                        .build())
                .phone("555-555-5555")
                .email("john@example.com")
                .shipToAddress(Address.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .billToAddress(Address.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .build();

        mockMvc.perform(post(CustomerController.BASE_URL)
                        .header("Authorization", basicDigestHeaderValue)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
    @DisplayName("Test List Customers")
    @Test
    void testListCustomers() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .header("Authorization", basicDigestHeaderValue)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }
    @DisplayName(("Get by Id"))
    @Test
    void testGetCustomerById() throws Exception {
        mockMvc.perform(get(BASE_URL+"/{customerId}", testCustomer.getId().toString())
                        .header("Authorization", basicDigestHeaderValue)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCustomer.getId().toString()));
    }
}
