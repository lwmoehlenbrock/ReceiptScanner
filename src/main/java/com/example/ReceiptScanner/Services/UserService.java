package com.example.ReceiptScanner.Services;

import com.example.ReceiptScanner.Accounting.Account;
import com.example.ReceiptScanner.Exceptions.InvalidUserException;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getallUsers(){
        return userRepository.findAll();
    }



    public void deleteAllUsers () {
        userRepository.deleteAll();
    }

    public void setBudget(Long userID, double budget) {
        Optional<User> myUser = userRepository.findById(userID);

        if (!myUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User foundUser = myUser.get();
        foundUser.setBudget(budget);
    }

    public void setSavingsGoal(Long userID, double savingsGoal) {
        Optional<User> myUser = userRepository.findById(userID);

        if (!myUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User foundUser = myUser.get();
        foundUser.setSavingsGoal(savingsGoal);
    }

    public double getSavingsGoal(Long userID) {
        Optional<User> myUser = userRepository.findById(userID);

        if (!myUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User foundUser = myUser.get();
        return foundUser.getSavingsGoal();
    }

    // TO DO
    public Account addAccount(Long userID, Account account) {
        return account;
    }

    // TO DO
    public double getAccountBalance(Long userID) {
        return 0;
    }
}
