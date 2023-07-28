package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.CartItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends ReactiveMongoRepository<CartItem, String> {
}
