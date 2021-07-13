package com.example.ReceiptScanner.Repositories;

import com.example.ReceiptScanner.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
