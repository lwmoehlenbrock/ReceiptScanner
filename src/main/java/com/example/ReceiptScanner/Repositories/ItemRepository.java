package com.example.ReceiptScanner.Repositories;

import com.example.ReceiptScanner.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
