package com.emard.apifirst.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static com.emard.apifirst.controllers.CustomerController.BASE_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomerControllerTest extends BaseTest {
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
