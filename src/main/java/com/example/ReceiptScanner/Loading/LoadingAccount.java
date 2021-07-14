package com.example.ReceiptScanner.Loading;

import com.example.ReceiptScanner.Model.Account;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.AccountRepository;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadingAccount {
    private static final Logger log = LoggerFactory.getLogger(LoadingAccount.class);

    /*@Bean
    CommandLineRunner initAccountDB(AccountRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Account("Checkings","Jaun Gomez",300)));
            log.info("Preloading " + repository.save(new Account("Savings","Jose Jamirez",400)));
        };
    }*/
}
