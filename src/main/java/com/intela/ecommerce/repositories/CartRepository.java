package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findByUserId(String id);
}
