package com.example.ReceiptScanner.Services;

import com.example.ReceiptScanner.Exceptions.InvalidUserException;
import com.example.ReceiptScanner.Model.Account;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.AccountRepository;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public Account addAccount(Long userID, Account account) {

        Optional<User> accountUser = userRepository.findById(userID);

        if (!accountUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User thisUser = accountUser.get();
        account.setUser(thisUser);
        return accountRepository.save(account);
    }


    public double getAccountBalance(Long userID, String accountName) {

        Optional<User> accountUser = userRepository.findById(userID);

        if (!accountUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User thisUser = accountUser.get();
        List<Account> thisUsersAccounts = thisUser.getAccounts();
        for (Account account : thisUsersAccounts) {
            if (account.getAccountName().equals(accountName)) {
                return account.getBalance();
            }
        }
        return 0;
    }
}
