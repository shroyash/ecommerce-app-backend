package com.shroyash.multi_shop.service;


import com.shroyash.multi_shop.dto.ProductDTO;
import com.shroyash.multi_shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

    Page<ProductDTO> getProductsByCategory(String category, Pageable pageable);

    List<ProductDTO> getProductsBySubCategory(String subCategory);

    List<ProductDTO> getProductsSortedByPriceAsc();

    List<ProductDTO> getProductsSortedByPriceDesc();

    List<ProductDTO> searchProducts(String keyword);

    List<ProductDTO> getBestsellerProducts();
}
