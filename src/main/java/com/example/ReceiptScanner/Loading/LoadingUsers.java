//package com.example.ReceiptScanner.Loading;
//
//import com.example.ReceiptScanner.Model.Receipt;
//import com.example.ReceiptScanner.Model.User;
//import com.example.ReceiptScanner.Repositories.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LoadingUsers {
//    private static final Logger log = LoggerFactory.getLogger(LoadingUsers.class);
//    List<User> UserList = new ArrayList<>();
//    List<Receipt> receiptList = new ArrayList<>();
//    List<Receipt> receiptList = new ArrayList<>();
//
//    @Bean
//    CommandLineRunner initCustomerDB(UserRepository repository) {
//        return args -> {
//            log.info("Preloading " + repository.save(new User("Juan taco", 200,300,
//    }
//}
