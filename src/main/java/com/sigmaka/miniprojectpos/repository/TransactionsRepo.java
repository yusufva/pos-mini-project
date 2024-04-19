package com.sigmaka.miniprojectpos.repository;

import com.sigmaka.miniprojectpos.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends JpaRepository<TransactionsEntity, Integer> {
}
