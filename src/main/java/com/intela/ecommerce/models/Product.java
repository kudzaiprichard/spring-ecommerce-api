package com.intela.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Product {
    @Id
    private String id;
    private String name;
    private Integer weight;
    private String color;
    private Integer quantity;
    private Integer price;
    private Category category;
    private List<Image> images;
}
