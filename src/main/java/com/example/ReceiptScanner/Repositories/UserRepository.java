package com.example.ReceiptScanner.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ReceiptScanner.*;

public interface UserRepository extends JpaRepository<User, Long> {
}
