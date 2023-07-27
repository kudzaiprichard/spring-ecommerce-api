package com.intela.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Token{
    @Id
    private String id;
    private String Token;
    private Boolean expired;
    private Boolean revoked;
    private TokenType tokenType;
    private User user;
}
