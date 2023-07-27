package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token,Integer> {
    @Query(
        """
        """
    )
    List<Token> findAllValidTokenByUser(String userId);
    Optional<Token> findByToken(String token);
}
