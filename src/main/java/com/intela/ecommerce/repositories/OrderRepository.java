package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order,String> {
}
