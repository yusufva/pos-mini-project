package com.sigmaka.miniprojectpos.repository;

import com.sigmaka.miniprojectpos.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends JpaRepository<CategoriesEntity, Integer> {
}
