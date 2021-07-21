package com.example.ReceiptScanner;

import com.example.ReceiptScanner.Model.User;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveUser() {
        User user = new User("Adriaan", 300, 400);

        User savedUser = repo.save(user);

        Optional<User> thisUser = repo.findById(1L);
        boolean actual = false;
        if (thisUser.isPresent()) {
            actual = true;
        }
        //User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(actual).isEqualTo(true);
    }

    @Test
    void testFindAll() {
        User user = new User("Adriaan", 300, 400);

        User savedUser = repo.save(user);

        List<User> thisUser = repo.findAll();
        boolean actual = false;
        System.out.println(thisUser.size());
        if (thisUser.size() == 2) {
            actual = true;
        }
        //User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(actual).isEqualTo(true);
    }

    @Test
    void testDeleteAll() {
        User user = new User("Adriaan", 300, 400);

        User savedUser = repo.save(user);

        repo.deleteAll();
        List<User> thisUser = repo.findAll();
        boolean actual = false;
        if (thisUser.size() == 0) {
            actual = true;
        }
        //User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(actual).isEqualTo(true);
    }
}
