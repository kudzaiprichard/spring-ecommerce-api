package com.intela.ecommerce.requestResponse;

import com.intela.ecommerce.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private String id;
    private Product product;
}
