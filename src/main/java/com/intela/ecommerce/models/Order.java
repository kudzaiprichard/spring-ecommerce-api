package com.intela.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Order {
    @Id
    private String id;
    private Cart cart;
    private OrderStatus orderStatus;
    private Date createdAt;
    private Date processedAt;
    private Long total;
}
