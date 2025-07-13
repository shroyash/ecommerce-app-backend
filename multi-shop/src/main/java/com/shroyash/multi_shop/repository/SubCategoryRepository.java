package com.shroyash.multi_shop.repository;



import com.shroyash.multi_shop.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long>{

    Optional<SubCategory> findByName(String subCategoryName);
}
