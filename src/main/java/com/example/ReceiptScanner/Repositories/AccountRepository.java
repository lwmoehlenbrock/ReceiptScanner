package com.example.ReceiptScanner.Repositories;

import com.example.ReceiptScanner.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByAccountName(String accountName);
}
