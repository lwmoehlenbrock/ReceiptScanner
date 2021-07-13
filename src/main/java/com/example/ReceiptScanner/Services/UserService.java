package com.example.ReceiptScanner.Services;

import com.example.ReceiptScanner.Exceptions.InvalidUserException;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
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

    /*public double getAccountBalance(Long userID) {
        Optional<User> myUser = userRepository.findById(userID);

        if (!myUser.isPresent()) {
            throw new InvalidUserException(""+userID);
        }
        User foundUser = myUser.get();
        return foundUser.getAccounts()
    }*/
}
