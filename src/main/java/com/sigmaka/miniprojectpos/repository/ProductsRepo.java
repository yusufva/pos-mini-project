package com.sigmaka.miniprojectpos.repository;

import com.sigmaka.miniprojectpos.entity.ProductsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<ProductsEntity, Integer> {
    List<ProductsEntity> findByCategory_Id(int categoryId);
    List<ProductsEntity> findByTitleContainingIgnoreCase(String title, Sort sort);
}
