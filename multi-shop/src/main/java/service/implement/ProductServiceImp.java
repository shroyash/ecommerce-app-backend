package service.implement;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.ProductRepository;
import service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getProductsByCategory(String category , Pageable pageable) {
        return productRepository.findByCategory(category,pageable);
    }

    @Override
    public List<Product> getProductsBySubCategory(String subCategory) {
        // Implement based on your repository method, or return empty for now
        return List.of();
    }

    @Override
    public List<Product> getProductsSortedByPriceAsc() {
        // Implement with repository method or return all products sorted
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    @Override
    public List<Product> getProductsSortedByPriceDesc() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
