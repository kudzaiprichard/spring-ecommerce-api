package com.intela.ecommerce.repositories;

import com.intela.ecommerce.models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token,String> {
    @Query(
        """
            db.token.find(
                {
                    "expired": false,
                    "revoked":  false,
                    "user._id": ObjectId(?0)
                }
            )
        """
    )
    List<Token> findAllValidTokenByUser(String userId);
    Optional<Token> findByToken(String token);
}
