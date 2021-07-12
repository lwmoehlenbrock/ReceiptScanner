package com.example.ReceiptScanner.Repositories;

import com.example.ReceiptScanner.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
