package mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import model.Product;
import dto.ProductDTO;
import model.Category;
import model.SubCategory;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category", target = "category", qualifiedByName = "categoryToString")
    @Mapping(source = "subCategory", target = "subCategory", qualifiedByName = "subCategoryToString")
    ProductDTO toDto(Product product);

    @Mapping(source = "category", target = "category", qualifiedByName = "stringToCategory")
    @Mapping(source = "subCategory", target = "subCategory", qualifiedByName = "stringToSubCategory")
    Product toEntity(ProductDTO productDTO);

    @Named("categoryToString")
    default String categoryToString(Category category) {
        return category == null ? null : category.getName();
    }

    @Named("subCategoryToString")
    default String subCategoryToString(SubCategory subCategory) {
        return subCategory == null ? null : subCategory.getName();
    }

    @Named("stringToCategory")
    default Category stringToCategory(String category) {
        return category == null ? null : Category.valueOf(category);
    }

    @Named("stringToSubCategory")
    default SubCategory stringToSubCategory(String subCategory) {
        return subCategory == null ? null : SubCategory.valueOf(subCategory);
    }
}
