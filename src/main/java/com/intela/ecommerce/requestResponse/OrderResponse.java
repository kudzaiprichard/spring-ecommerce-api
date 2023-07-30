package com.intela.ecommerce.requestResponse;

import com.intela.ecommerce.models.Cart;
import com.intela.ecommerce.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String id;
    private Cart cart;
    private OrderStatus orderStatus;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private Integer total;
}
