package com.example.ReceiptScanner.Controllers;

import com.example.ReceiptScanner.Model.Account;
import com.example.ReceiptScanner.Services.AccountService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    protected AccountController(){

    }

    @CrossOrigin
    @PostMapping("/addAccount")
    public Account addAccount(@RequestBody ObjectNode objectNode) {
        Long id = objectNode.get("id").asLong();
        String accountType = objectNode.get("accountType").toString();
        String accountName = objectNode.get("accountName").toString();
        double balance = objectNode.get("balance").asDouble();
        Account account = new Account(accountType, accountName, balance);

        return accountService.addAccount(id, account);
    }

    @CrossOrigin
    @GetMapping("/getBalance")
    public double getAccountBalance(@RequestBody ObjectNode objectNode) {
        Long id = objectNode.get("id").asLong();
        String accountName = objectNode.get("accountName").toString();

        return accountService.getAccountBalance(id, accountName);
    }

    @CrossOrigin
    @PutMapping("/updateBalance")
    public void updateAccountBalance(@RequestBody ObjectNode objectNode) {
        Long id = objectNode.get("id").asLong();
        String accountName = objectNode.get("accountName").toString();
        double newBalance = objectNode.get("newBalance").asDouble();
        accountService.updateAccountBalance(id, accountName, newBalance);
    }

    @CrossOrigin
    @DeleteMapping("/removeAccount")
    public void removeAccount(@RequestBody ObjectNode objectNode) {
        Long id = objectNode.get("id").asLong();
        String accountName = objectNode.get("accountName").toString();
        accountService.removeAccount(id, accountName);
    }
}
