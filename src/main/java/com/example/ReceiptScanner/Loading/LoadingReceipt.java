package com.example.ReceiptScanner.Loading;

import com.example.ReceiptScanner.Model.Receipt;
import com.example.ReceiptScanner.Repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadingReceipt {
    private static final Logger log = LoggerFactory.getLogger(LoadingReceipt.class);

//    @Bean
//    CommandLineRunner initReceiptDB(AccountRepository repository) {
//        return args -> {
//            //log.info("Preloading " + repository.save(new Receipt("Checkings","Jaun Gomez",300)));
//        };
//    }
}





