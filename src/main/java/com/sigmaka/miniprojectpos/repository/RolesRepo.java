package com.sigmaka.miniprojectpos.repository;

import com.sigmaka.miniprojectpos.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<RolesEntity, Integer> {
}
