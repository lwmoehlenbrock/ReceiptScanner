package com.example.ReceiptScanner.Services;

import com.example.ReceiptScanner.Exceptions.DuplicateAccountException;
import com.example.ReceiptScanner.Exceptions.InvalidUserException;
import com.example.ReceiptScanner.Model.Account;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.AccountRepository;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(AccountService.class);

    public Account addAccount(Long userID, Account account) {

        Optional<User> accountUser = userRepository.findById(userID);

        if (!accountUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User thisUser = accountUser.get();
        account.setUser(thisUser);

        for (Account currentAccount : thisUser.getAccounts()) {
            if (currentAccount.getAccountName().equals(account.getAccountName())) {
                throw new DuplicateAccountException("This account already exists");
            }
        }

        thisUser.getAccounts().add(account);
        userRepository.save(thisUser);
        return accountRepository.save(account);
    }

    public void removeAccount(Long userID, String accountName) {
        Optional<User> accountUser = userRepository.findById(userID);

        if (!accountUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }

        User thisUser = accountUser.get();
        List<Account> thisUsersAccounts = thisUser.getAccounts();
        for (Account account : thisUsersAccounts) {
            if (account.getAccountName().equals(accountName)) {
                accountRepository.delete(account);
            }
        }
    }

    public double getAccountBalance(Long userID, String accountName) {

        Optional<User> accountUser = userRepository.findById(userID);

        if (!accountUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User thisUser = accountUser.get();
        log.info(thisUser.toString());
        List<Account> thisUsersAccounts = thisUser.getAccounts();
        for (Account account : thisUsersAccounts) {
            if (account.getAccountName().equals(accountName)) {
                return account.getBalance();
            }
        }
        return 0;
    }

    public void updateAccountBalance(Long userID, String accountName, double newBalance) {

        Optional<User> accountUser = userRepository.findById(userID);

        log.info("updating");
        if (!accountUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User thisUser = accountUser.get();
        List<Account> thisUsersAccounts = thisUser.getAccounts();
        for (Account account : thisUsersAccounts) {
            if (account.getAccountName().equals(accountName)) {
                log.info("Account found");
                account.setBalance(account.getBalance() + newBalance);
                accountRepository.save(account);
            }
        }
        userRepository.save(thisUser);
    }
}
