package com.emard.apifirst.services;

import ord.emard.apifirst.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> listProducts();

    Product getProductById(UUID productId);
}
