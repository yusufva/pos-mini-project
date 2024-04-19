package com.sigmaka.miniprojectpos.repository;

import com.sigmaka.miniprojectpos.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsDetailRepo extends JpaRepository<TransactionDetailsEntity, Integer> {
}
