package com.intela.ecommerce.requestResponse;

import com.intela.ecommerce.models.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String id;
    private LoggedUserResponse user;
    private List<CartItem> cartItems;
}
