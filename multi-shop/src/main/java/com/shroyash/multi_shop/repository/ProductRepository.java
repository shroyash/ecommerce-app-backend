package com.shroyash.multi_shop.repository;



import com.shroyash.multi_shop.dto.ProductDTO;
import com.shroyash.multi_shop.model.Category;
import com.shroyash.multi_shop.model.Product;
import com.shroyash.multi_shop.model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategory(Category category, Pageable pageable);

    List<Product> findBySubCategory(SubCategory subCategory);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByBestsellerTrue();

}

