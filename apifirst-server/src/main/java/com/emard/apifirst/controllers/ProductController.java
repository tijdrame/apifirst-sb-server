package com.emard.apifirst.controllers;

import com.emard.apifirst.services.ProductService;
import lombok.RequiredArgsConstructor;
import ord.emard.apifirst.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.emard.apifirst.controllers.ProductController.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/v1/products";

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> saveNewProduct(@RequestBody Product product){
        Product savedProduct = productService.saveNewProduct(product);

        UriComponents uriComponents = UriComponentsBuilder.fromPath(BASE_URL + "/{product_id}")
                .buildAndExpand(savedProduct.getId());

        return ResponseEntity.created(URI.create(uriComponents.getPath())).build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(){
        return ResponseEntity.ok(productService.listProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") UUID productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
