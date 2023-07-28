package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
