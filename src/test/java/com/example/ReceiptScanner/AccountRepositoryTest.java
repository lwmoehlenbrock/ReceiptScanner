package com.example.ReceiptScanner;

import com.example.ReceiptScanner.Model.Account;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.AccountRepository;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveAccount() {
        Account userAccount = new Account("Checking", "Primary Checking", 300);

        Account savedUser = repo.save(userAccount);

        Optional<Account> thisUser = repo.findById(1L);
        boolean actual = false;
        if (thisUser.isPresent()) {
            actual = true;
        }
        //User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(actual).isEqualTo(true);
    }

    @Test
    void testDeleteAccount() {
        Account userAccount = new Account("Checking", "Primary Checking", 300);

        Account savedUser = repo.save(userAccount);
        repo.delete(savedUser);
        Optional<Account> thisUser = repo.findById(1L);
        boolean actual = false;
        if (thisUser.isPresent()) {
            actual = true;
        }
        //User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(actual).isEqualTo(false);
    }
}
