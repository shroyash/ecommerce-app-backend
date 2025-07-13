package com.shroyash.multi_shop.mapper;

import com.shroyash.multi_shop.dto.ProductDTO;
import com.shroyash.multi_shop.model.Category;
import com.shroyash.multi_shop.model.Product;
import com.shroyash.multi_shop.model.SubCategory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T05:55:17+0545",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        List<String> list = product.getImage();
        if ( list != null ) {
            productDTO.setImageUrl( new ArrayList<String>( list ) );
        }
        productDTO.setFeatured( product.isBestseller() );
        productDTO.setCategory( productCategoryName( product ) );
        productDTO.setSubCategory( productSubCategoryName( product ) );
        productDTO.setCategoryId( productCategoryId( product ) );
        productDTO.setSubCategoryId( productSubCategoryId( product ) );
        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        List<String> list1 = product.getSizes();
        if ( list1 != null ) {
            productDTO.setSizes( new ArrayList<String>( list1 ) );
        }
        productDTO.setDate( product.getDate() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO dto, Category category, SubCategory subCategory) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        List<String> list = dto.getImageUrl();
        if ( list != null ) {
            product.setImage( new ArrayList<String>( list ) );
        }
        product.setBestseller( dto.isFeatured() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        List<String> list1 = dto.getSizes();
        if ( list1 != null ) {
            product.setSizes( new ArrayList<String>( list1 ) );
        }

        product.setDate( System.currentTimeMillis() );

        setRelations( product, category, subCategory );

        return product;
    }

    @Override
    public void updateEntity(Product product, ProductDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            product.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            product.setDescription( dto.getDescription() );
        }
        product.setPrice( dto.getPrice() );
        if ( product.getSizes() != null ) {
            List<String> list = dto.getSizes();
            if ( list != null ) {
                product.getSizes().clear();
                product.getSizes().addAll( list );
            }
        }
        else {
            List<String> list = dto.getSizes();
            if ( list != null ) {
                product.setSizes( new ArrayList<String>( list ) );
            }
        }
        if ( dto.getDate() != null ) {
            product.setDate( dto.getDate() );
        }
    }

    private String productCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String productSubCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        SubCategory subCategory = product.getSubCategory();
        if ( subCategory == null ) {
            return null;
        }
        String name = subCategory.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long productCategoryId(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long productSubCategoryId(Product product) {
        if ( product == null ) {
            return null;
        }
        SubCategory subCategory = product.getSubCategory();
        if ( subCategory == null ) {
            return null;
        }
        Long id = subCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
