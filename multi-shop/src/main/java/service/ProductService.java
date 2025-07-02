package service;

import dto.ProductDTO;
import model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product createProduct(ProductDTO product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Page<Product> getProductsByCategory(String category, Pageable pageable);

    List<Product> getProductsBySubCategory(String subCategory);

    List<Product> getProductsSortedByPriceAsc();

    List<Product> getProductsSortedByPriceDesc();

    List<Product> searchProducts(String keyword);
}
