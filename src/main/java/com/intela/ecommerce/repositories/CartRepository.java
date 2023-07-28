package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends ReactiveMongoRepository<Cart, String> {
}
