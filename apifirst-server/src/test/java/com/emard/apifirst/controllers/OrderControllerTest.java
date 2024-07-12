package com.emard.apifirst.controllers;

import org.junit.jupiter.api.Test;

import static com.emard.apifirst.controllers.OrderController.BASE_URL;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends BaseTest{

    @Test
    void listOrders() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .header("Authorization", basicDigestHeaderValue)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void getProductById() throws Exception {
        mockMvc.perform(get(BASE_URL+"/{orderId}", testOrder.getId())
                        .header("Authorization", basicDigestHeaderValue)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testOrder.getId().toString()));
    }
}