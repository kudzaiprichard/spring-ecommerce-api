package com.intela.ecommerce.services;

import com.intela.ecommerce.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ImageRepository imageRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;

    /*
     * Products functions
     */
    //Todo: fetch all products

    //Todo: fetch product by id

    //Todo: fetch product images by product id


    /*
     * Cart functions
     */
    //Todo: add product in user cart

    //Todo: remove product in user cart

    //Todo: increase or decrease product quantity in cart


    /*
     * Order functions
     */
    //include filters for pending, fulfilled and rejected orders
    //Todo: fetch all orders

    //Todo: create an order by cart id


}
