package com.emard.apifirst.repositories;

import ord.emard.apifirst.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface ProductRepository extends CrudRepository<Product, UUID> {
}
