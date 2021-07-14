package com.example.ReceiptScanner.Controllers;

import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.UserRepository;
import com.example.ReceiptScanner.Services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {

    }

    @GetMapping(value = "/")
    @CrossOrigin
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping("/")
    public void addAUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @CrossOrigin
    @DeleteMapping("/removeAll")
    public void delete(@RequestBody User user) {
        userService.deleteAllUsers();
    }

    @CrossOrigin
    @PutMapping("/updateBudget")
    public void setBudget(@RequestBody ObjectNode objectNode) {
        Long userID = objectNode.get("id").asLong();
        double budget = objectNode.get("budget").asDouble();
        userService.setBudget(userID, budget);
    }

    @CrossOrigin
    @PutMapping("/updateSavingsGoal")
    public void setSavingsGoal(@RequestBody ObjectNode objectNode) {
        Long userID = objectNode.get("id").asLong();
        double savingsGoal = objectNode.get("savingsGoal").asDouble();
        userService.setSavingsGoal(userID, savingsGoal);
    }

    @CrossOrigin
    @GetMapping("/getGoal")
    public double getSavingsGoal(@RequestBody ObjectNode objectNode) {
        Long userID = objectNode.get("id").asLong();
        return userService.getSavingsGoal(userID);
    }
}
