package com.sigmaka.miniprojectpos.repository;

import com.sigmaka.miniprojectpos.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity, Integer> {
    Optional<UsersEntity> findByEmail(String email);
}
