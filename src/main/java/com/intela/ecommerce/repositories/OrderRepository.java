package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findAllByCartId(String id);
}
