package com.emard.apifirst.repositories;

import ord.emard.apifirst.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OrderRepository extends CrudRepository<Order, UUID> {
}
