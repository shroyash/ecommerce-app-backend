package com.shroyash.multi_shop.mapper;

import com.shroyash.multi_shop.dto.ProductDTO;
import com.shroyash.multi_shop.model.Product;
import com.shroyash.multi_shop.model.Category;      // ✅ Correct import
import com.shroyash.multi_shop.model.SubCategory;   // ✅ Correct import
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Enables Spring to detect and inject this mapper as a bean
public interface ProductMapper {

    // Convert Entity → DTO
    // Useful when sending product data to frontend
    @Mapping(source = "image", target = "imageUrl") // image field in entity → imageUrl in DTO
    @Mapping(source = "bestseller", target = "featured") // bestseller (boolean) → featured (boolean)
    @Mapping(source = "category.name", target = "category") // category.name (nested) → category (String)
    @Mapping(source = "subCategory.name", target = "subCategory") // subCategory.name → subCategory (String)
    @Mapping(source = "category.id", target = "categoryId") // category.id → categoryId (Long)
    @Mapping(source = "subCategory.id", target = "subCategoryId") // subCategory.id → subCategoryId (Long)
    ProductDTO toDTO(Product product);

    // Convert DTO → Entity
    // Used when creating a new product from frontend data
    @Mapping(source = "imageUrl", target = "image") // imageUrl in DTO → image in entity
    @Mapping(source = "featured", target = "bestseller") // featured in DTO → bestseller in entity
    @Mapping(target = "category", ignore = true) // will set manually (we only get categoryId in DTO)
    @Mapping(target = "subCategory", ignore = true) // will set manually (we only get subCategoryId in DTO)
    @Mapping(target = "id", ignore = true) // DB will generate ID, not set from DTO
    @Mapping(target = "date", expression = "java(System.currentTimeMillis())") // set current timestamp for new product
    Product toEntity(ProductDTO dto, @Context Category category, @Context SubCategory subCategory); // ✅ Fixed imports

    // Custom logic that runs AFTER mapping (used to set relationships manually)
    @AfterMapping
    default void setRelations(
            @MappingTarget Product product, // Don't create a new Product object — instead, update this existing one."
            @Context Category category,     // ✅ Fixed import
            @Context SubCategory subCategory // ✅ Fixed import
    ) {
        product.setCategory(category); // attach full Category object
        product.setSubCategory(subCategory); // attach full SubCategory object
    }

    // Update only non-null fields from DTO into existing Product entity
    // Used for partial update (e.g., admin only wants to update price or description)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "subCategory", ignore = true)
    @Mapping(target = "id", ignore = true) // ✅ Added this to prevent ID updates
    void updateEntity(@MappingTarget Product product, ProductDTO dto);
}