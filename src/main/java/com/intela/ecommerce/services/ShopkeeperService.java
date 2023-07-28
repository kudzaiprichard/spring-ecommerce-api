package com.intela.ecommerce.services;

import com.intela.ecommerce.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopkeeperService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ImageRepository imageRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    /*
    * Function to manage products
    */
    //Todo: add products

    //Todo: add image to product with id

    //Todo: fetch all products

    //Todo: fetch product by id

    //Todo: fetch product images by product id

    //Todo: delete a product by id

    //Todo: update a product by id


    /*
     * Function to manage orders
     */
    //include filters for pending, fulfilled and rejected orders
    //Todo: fetch all order

    //Todo: fetch order by id

    //order processing includes setting the order status and date where it was processed
    //Todo: process order by id


}
