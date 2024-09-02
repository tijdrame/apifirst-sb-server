package com.emard.apifirst.controllers;

import ord.emard.apifirst.model.Category;
import ord.emard.apifirst.model.Dimentions;
import ord.emard.apifirst.model.Image;
import ord.emard.apifirst.model.Product;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static com.emard.apifirst.controllers.ProductController.BASE_URL;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends BaseTest{

    @Test
    void testCreateProduct() throws Exception {
        Product newProduct = Product.builder()
                .description("New Product")
                .cost("5.00")
                .price("8.95")
                .categories(Arrays.asList(Category.builder()
                        .category("New Category")
                        .description("New Category Description")
                        .build()))
                .images(Arrays.asList(Image.builder()
                        .url("http://example.com/image.jpg")
                        .altText("Image Alt Text")
                        .build()))
                .dimentions(Dimentions.builder()
                        .length(10)
                        .width(10)
                        .height(10)
                        .build())
                .build();

        mockMvc.perform(post(ProductController.BASE_URL)
                        .header("Authorization", basicDigestHeaderValue)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void listProducts() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .header("Authorization", basicDigestHeaderValue)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void getProductById() throws Exception {

        mockMvc.perform(get(BASE_URL+"/{productId}", testProduct.getId())
                        .header("Authorization", basicDigestHeaderValue)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testProduct.getId().toString()));
    }
}