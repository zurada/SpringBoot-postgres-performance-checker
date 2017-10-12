package com.azurada.repository;

import com.azurada.model.TransactionWithLong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionWithLongRepository extends JpaRepository<TransactionWithLong, Long> {
}
