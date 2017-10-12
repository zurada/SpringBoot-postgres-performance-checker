package com.azurada.repository;

import com.azurada.model.TransactionWithString;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionWithStringRepository extends JpaRepository<TransactionWithString, String> {
}
