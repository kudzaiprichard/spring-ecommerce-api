package com.intela.ecommerce.services;

import com.intela.ecommerce.models.Image;
import com.intela.ecommerce.models.Order;
import com.intela.ecommerce.models.OrderStatus;
import com.intela.ecommerce.models.Product;
import com.intela.ecommerce.repositories.*;
import com.intela.ecommerce.requestResponse.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public Product addProduct(ProductRequest request, MultipartFile[] images){
        List<Image> imageList = new ArrayList<>();

        //Todo: get all images and save them in a list
        try {
            return this.productRepository.save(
                    Product.builder()
                            .name(request.getName())
                            .color(request.getColor())
                            .price(request.getPrice())
                            .quantity(request.getQuantity())
                            .weight(request.getWeight())
                            .images(imageList)
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException("Could not save product");
        }
    }

    public Product addImageToProduct(String productId, MultipartFile[] images){
        List<Image> imageList = new ArrayList<>();
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Could not find product"));
        //Todo: get images and save them inside image list

        product.setImages(imageList);
        return this.productRepository.save(product);
    }

    public List<Product> fetchAllProducts(){
        return this.productRepository.findAll();
    }

    public Product fetchProductById(String productId){
        return this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Could not find product"));
    }

    public Product updateProductById(
            ProductRequest request,
            MultipartFile[] images,
            String productId
    ){
        List<Image> imageList = new ArrayList<>();
        Product dbProduct = this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Could not find request"));

        if(!request.getPrice().toString().isBlank()){
            dbProduct.setPrice(request.getPrice());
        }
        if(!request.getName().isBlank()){
            dbProduct.setName(request.getName());
        }
        if(!request.getColor().isBlank()){
            dbProduct.setColor(request.getColor());
        }
        if(!request.getWeight().toString().isBlank()){
            dbProduct.setWeight(request.getWeight());
        }
        if(!request.getQuantity().toString().isBlank()){
            dbProduct.setQuantity(request.getQuantity());
        }
        //Todo: update images
        if(images.length > 0){

        }

        return this.productRepository.save(dbProduct);
    }


    /*
     * Function to manage orders
     */
    //Todo: include filters for pending, fulfilled and rejected orders
    public List<Order> fetchAllOrders(){
        return this.orderRepository.findAll();
    }

    public Order fetchOderById(String orderId){
        return this.orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Could not find order"));
    }

    public Order processOrderById(OrderStatus orderStatus, String orderId){
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Could not find order"));

        if(orderStatus == null) throw new RuntimeException("Make sure you have selected order status");
        order.setProcessedAt(ZonedDateTime.now());
        order.setOrderStatus(orderStatus);
        return this.orderRepository.save(order);
    }


}
