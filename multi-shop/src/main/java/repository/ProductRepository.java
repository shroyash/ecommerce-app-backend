package repository;

import Model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Page<Product> findByCategory(String category, Pageable pageable);

    List<Product> findByPriceLessThan(Double price);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryOrderByPriceAsc(String category);

    List<Product> findByCategoryOrderByPriceDesc(String category);

    List<Product> findByFeaturedTrue();
}
