package com.shroyash.multi_shop.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private List<String> imageUrl;      // corresponds to image
    private List<String> sizes;
    private Long categoryId;            // for sending/receiving from client
    private Long subCategoryId;
    private String category;            // for response
    private String subCategory;
    private Long date;
    private boolean featured;           // maps to bestseller
}
