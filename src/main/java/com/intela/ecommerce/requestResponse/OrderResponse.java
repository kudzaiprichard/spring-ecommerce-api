package com.intela.ecommerce.requestResponse;

import com.intela.ecommerce.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String id;
    private CartResponse cart;
    private OrderStatus orderStatus;
    private Date createdAt;
    private Date processedAt;
    private Long total;
}
