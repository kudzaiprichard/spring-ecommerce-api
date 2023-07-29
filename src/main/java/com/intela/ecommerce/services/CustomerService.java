package com.intela.ecommerce.services;

import com.intela.ecommerce.models.*;
import com.intela.ecommerce.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.intela.ecommerce.util.Util.getUserByToken;

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
    private JwtService jwtService;

    /*
     * Products functions
     */
    //Todo: fetch all products
    public List<Product> fetchAllProducts(){
        return this.productRepository.findAll();
    }

    //Todo: fetch product by id
    public Product fetchProductById(String productId){
        return this.productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Could not find product"));
    }

    /*
     * Cart functions
     */
    //Todo: add product in user cart
    public Cart addProductInCart(
            String productId,
            HttpServletRequest request
    ){
        User user = getUserByToken(request, jwtService, this.userRepository);
        Cart cart = this.cartRepository.findByUserId(user.getId());
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Could not find product"));

        //check if cart instance exists
        //if not create a new cart for the user
        if(cart.getId().isBlank()){
            List<CartItem> cartItems = new ArrayList<>();
            //Create a new cart item and add it in a list
            CartItem savedCartItem = this.cartItemRepository.save(
                    CartItem.builder()
                            .product(product)
                            .quantity(1)
                            .build()
            );
            cartItems.add(savedCartItem);

            //create a new cart item and add them to the new cart
            Cart newCart = Cart.builder()
                    .user(user)
                    .cartItems(cartItems)
                    .build();

            //save the new cart in the database
            return this.cartRepository.save(newCart);
        }
        else {
            boolean productExist = true;
            //get all as a list cart items from cart
            //check if product already exists in one of the cart items
            for (CartItem cartItem : cart.getCartItems()) {
                if (Objects.equals(cartItem.getProduct().getId(), product.getId())) {
                    //if product exist then add a 1 to the quantity of the product item
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    productExist = false;
                    break;
                }
            }

            //if product does not exist
            if(!productExist){
                //create a new cart item with product and quantity as 1
                CartItem savedCartItem = this.cartItemRepository.save(
                        CartItem.builder()
                                .product(product)
                                .quantity(1)
                                .build()
                );
                //add the cart item in the cart
                cart.getCartItems().add(savedCartItem);
            }

            //save,update and return the cart item
            return this.cartRepository.save(cart);
        }

    }

    //Todo: remove product in user cart
    public String removeProductFromCart(String cartItemId){
        try{
            this.cartItemRepository.deleteById(cartItemId);
        }catch (Exception e){
             throw new RuntimeException("Failed to remove product from cart");
        }
        return "Product successfully removed from cart";
    }

    //Todo: increase or decrease product quantity in cart
    public CartItem increaseDecreaseProductQuantity(String cartItemId, Integer value){
        CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Could not find cart item"));

        if(value > 0 ){
            cartItem.setQuantity(cartItem.getQuantity() + value);
            return this.cartItemRepository.save(cartItem);
        }
        else{
            throw new RuntimeException("Product quantity should be above zero");
        }
    }

    /*
     * Order functions
     */
    //include filters for pending, fulfilled and rejected orders
    //Todo: fetch all orders by user
    public List<Order> fetchAllOrdersByUserId(
            HttpServletRequest request
    ){
        User user = getUserByToken(request, jwtService, this.userRepository);
        Cart cart = this.cartRepository.findByUserId(user.getId());
        return this.orderRepository.findAllByCartId(cart.getId());
    }

    //Todo: create an order by cart id
    public Order createOrder(String cartId, HttpServletRequest request){
        int total = 0;
        User user = getUserByToken(request, jwtService, this.userRepository);
        Cart cart = this.cartRepository.findByUserId(user.getId());

        for(CartItem cartItem : cart.getCartItems()){
            total = total + (cartItem.getQuantity() * cartItem.getProduct().getPrice());
        }

        return this.orderRepository.save(
                Order.builder()
                        .orderStatus(OrderStatus.PENDING)
                        .cart(cart)
                        .createdAt(ZonedDateTime.now())
                        .processedAt(null)
                        .total(total)
                        .build()
        );
    }


}
