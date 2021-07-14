package com.example.ReceiptScanner.Controllers;

import com.example.ReceiptScanner.Model.Receipt;
import com.example.ReceiptScanner.Services.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/scan/{id}")
    public void scanReceipt(@ModelAttribute MultipartFile image, @PathVariable Long id) throws Exception {

        receiptService.scanReceipt(image, id);
    }

    @GetMapping("/find/{id}")
    public Optional<Receipt> findReceiptById(@PathVariable Long id){
        return receiptService.findReceiptById(id);
    }

    @GetMapping("/find")
    public List<Receipt> findAllReceipts(){
        return receiptService.getAllReceipts();
    }
}
