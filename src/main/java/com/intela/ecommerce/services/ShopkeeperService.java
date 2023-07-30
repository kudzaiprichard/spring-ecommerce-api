package com.intela.ecommerce.services;

import com.intela.ecommerce.models.*;
import com.intela.ecommerce.repositories.*;
import com.intela.ecommerce.requestResponse.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopkeeperService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    @Value(value = "${application.file-system.folder-path}")
    private String FOLDER_PATH;

    /*
    * Function to manage products
    */
    private void multipartFileToImageList(MultipartFile[] imagesRequest, List<Image> images) {

        Arrays.asList(imagesRequest).forEach(
                imageRequest -> {
                    String filePath = FOLDER_PATH + imageRequest.getOriginalFilename();
                     Image image = Image.builder()
                            .image(filePath)
                            .name(imageRequest.getOriginalFilename())
                            .type(imageRequest.getContentType())
                            .build();
                    try {
                        imageRequest.transferTo(new File(filePath));
                        images.add(this.imageRepository.save(image));
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to upload file" + e);
                    }
                }
        );
    }

    public Product addProduct(ProductRequest request, MultipartFile[] images){
        List<Image> imageList = new ArrayList<>();
        Category savedCategory = this.categoryRepository.save(
                Category.builder()
                        .categoryName(request.getCategory())
                        .build()
        );

        multipartFileToImageList(images,imageList);

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
                            .category(savedCategory)
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException("Could not save product");
        }
    }

    public List<Category> fetchAllCategories(){
        return this.categoryRepository.findAll();
    }

    public Product addImageToProduct(String productId, MultipartFile[] images){
        List<Image> imageList = new ArrayList<>();
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Could not find product"));

        multipartFileToImageList(images,imageList);

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

        if(!request.getPrice().toString().isBlank())
            dbProduct.setPrice(request.getPrice());

        if(!request.getName().isBlank())
            dbProduct.setName(request.getName());

        if(!request.getColor().isBlank())
            dbProduct.setColor(request.getColor());

        if(!request.getWeight().toString().isBlank())
            dbProduct.setWeight(request.getWeight());

        if(!request.getQuantity().toString().isBlank())
            dbProduct.setQuantity(request.getQuantity());

        if(!request.getCategory().isBlank())
            dbProduct.getCategory().setCategoryName(request.getCategory());


        if(images.length > 0){
            multipartFileToImageList(images,imageList);
            dbProduct.setImages(imageList);
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

    public Category createCategory(String category) {
        return this.categoryRepository.save(
                Category.builder().categoryName(category).build()
        );
    }
}
