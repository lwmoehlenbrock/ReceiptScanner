package com.example.ReceiptScanner.Controllers;

import com.example.ReceiptScanner.Model.Receipt;
import com.example.ReceiptScanner.Services.AccountService;
import com.example.ReceiptScanner.Services.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    Logger log = LoggerFactory.getLogger(ReceiptController.class);

    @Autowired
    ReceiptService receiptService;



    @PostMapping("/scan/{id}&{accountName}")
    public void scanReceipt(@ModelAttribute MultipartFile image, @PathVariable("id") Long id, @PathVariable("accountName") String accountName, HttpServletResponse response) throws Exception {

        log.info("Account Name: " + accountName);
        Receipt receipt = receiptService.scanReceipt(image, id);

        receiptService.updateAccountBalanceFromReceipt(id, accountName, -receipt.getTotal());
        //double updateBalance = -receipt.getTotal();
        //response.sendRedirect("/accounts/updateBalance/" + id + "&" + accountName + "&" + updateBalance);

        //log.info(String.valueOf(accountService.getAccountBalance(id, accountName) - receipt.getTotal()));
    }

    @GetMapping("/find/{id}")
    public Optional<Receipt> findReceiptById(@PathVariable Long id){
        return receiptService.findReceiptById(id);
    }

    @GetMapping("/find")
    public String findAllReceipts(){
        log.info(receiptService.getAllReceipts());
        return receiptService.getAllReceipts();
    }
}
