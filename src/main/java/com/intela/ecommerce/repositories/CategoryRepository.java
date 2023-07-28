package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category,String> {
}
