package com.example.ReceiptScanner.Controllers;

import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController1 {


        private UserService userService;

        @Autowired
        public UserController1(UserService userService) {
            this.userService = userService;
        }

        public UserController1() {

        }

        @GetMapping(value = "/")
        @CrossOrigin
        public List<User> getallUsers() {
            return userService.getallUsers();
        }

        @CrossOrigin
        @PostMapping("/")
        public void addAUser(@RequestBody User user) {
            userService.createUser(user);
        }

        @DeleteMapping
        @CrossOrigin
        public void delete(@RequestBody User user) {
            userService.deleteAllUsers();


        }

}
