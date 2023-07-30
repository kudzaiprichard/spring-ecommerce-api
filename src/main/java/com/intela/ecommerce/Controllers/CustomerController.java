package com.intela.ecommerce.Controllers;

import com.intela.ecommerce.models.CartItem;
import com.intela.ecommerce.models.Order;
import com.intela.ecommerce.models.Product;
import com.intela.ecommerce.requestResponse.CartResponse;
import com.intela.ecommerce.requestResponse.OrderResponse;
import com.intela.ecommerce.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/")
    public List<Product> fetchAllProducts() {
        return this.customerService.fetchAllProducts();
    }
    @GetMapping("/product/{productId}")
    public Product fetchProductById(@PathVariable String productId) {
        return this.customerService.fetchProductById(productId);
    }

    @PostMapping("/cart/add/{productId}")
    public CartResponse addProductInCart(@PathVariable String productId,
                                         HttpServletRequest request
    ) {
        return this.customerService.addProductInCart(productId, request);
    }

    @DeleteMapping("/cart/remove/{cartItemId}")
    public String removeProductFromCart(@PathVariable String cartItemId) {
        return this.customerService.removeProductFromCart(cartItemId);
    }

    @GetMapping("/cart")
    public CartResponse fetchCartByUserId(HttpServletRequest request){
        return this.customerService.fetchCartByUserId(request);
    }
    @PostMapping("/cart/product/quantity/{cartItemId}/{value}")
    public CartItem increaseDecreaseProductQuantity(
            @PathVariable String cartItemId,
            @PathVariable Integer value
    ) {
        return this.customerService.increaseDecreaseProductQuantity(cartItemId, value);
    }

    @GetMapping("/orders")
    public List<Order> fetchAllOrdersByUserId(HttpServletRequest request) {
        return this.customerService.fetchAllOrdersByUserId(request);
    }

    @PostMapping("/order/create")
    public OrderResponse createOrder(HttpServletRequest request) {
        return this.customerService.createOrder(request);
    }
}
