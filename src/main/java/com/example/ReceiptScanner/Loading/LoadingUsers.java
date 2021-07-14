package com.example.ReceiptScanner.Loading;

import com.example.ReceiptScanner.Model.Account;
import com.example.ReceiptScanner.Model.Receipt;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadingUsers {
    private static final Logger log = LoggerFactory.getLogger(LoadingUsers.class);
    List<User> UserList = new ArrayList<>();
    //List<Receipt> receiptList = new ArrayList<>();
    List<Account> accountsList = new ArrayList<>();


    @Bean
    CommandLineRunner initUserDB(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("Alex Canseco", 200,300,accountsList)));
            log.info("Preloading " + repository.save(new User("Salena Gomez", 50000,200000,accountsList)));

        };
}
}
