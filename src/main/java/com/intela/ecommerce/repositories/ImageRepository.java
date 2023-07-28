package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Image;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends ReactiveMongoRepository<Image,String> {
}
