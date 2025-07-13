package com.shroyash.multi_shop.service.implement;

import com.shroyash.multi_shop.dto.ProductDTO;
import com.shroyash.multi_shop.mapper.ProductMapper;
import com.shroyash.multi_shop.model.Category;
import com.shroyash.multi_shop.model.Product;
import com.shroyash.multi_shop.model.SubCategory;
import com.shroyash.multi_shop.repository.CategoryRepository;
import com.shroyash.multi_shop.repository.ProductRepository;
import com.shroyash.multi_shop.repository.SubCategoryRepository;
import com.shroyash.multi_shop.service.ProductService;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public ProductServiceImp(ProductRepository productRepository,
                             ProductMapper productMapper,
                             CategoryRepository categoryRepository,
                             SubCategoryRepository subCategoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Transactional(readOnly = true)          // make sure a session is open
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()          // entities are still attached
                .stream()
                .peek(p -> Hibernate.initialize(p.getImage())) // force load
                .map(productMapper::toDTO)          // mapper can now read images
                .toList();
    }


    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        SubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));

        Product product = productMapper.toEntity(dto, category, subCategory);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productMapper.updateEntity(existingProduct, dto);

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        if (dto.getSubCategoryId() != null) {
            SubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                    .orElseThrow(() -> new RuntimeException("SubCategory not found"));
            existingProduct.setSubCategory(subCategory);
        }

        Product savedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> getProductsByCategory(String categoryName, Pageable pageable) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));

        return productRepository.findByCategory(category, pageable)
                .map(productMapper::toDTO);
    }

    @Override
    public List<ProductDTO> getProductsBySubCategory(String subCategoryName) {
        SubCategory subCategory = subCategoryRepository.findByName(subCategoryName)
                .orElseThrow(() -> new RuntimeException("SubCategory not found: " + subCategoryName));

        return productRepository.findBySubCategory(subCategory).stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getProductsSortedByPriceAsc() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price")).stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getProductsSortedByPriceDesc() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "price")).stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getBestsellerProducts() {
        return productRepository.findByBestsellerTrue().stream()
                .map(productMapper::toDTO)
                .toList();
    }
}
