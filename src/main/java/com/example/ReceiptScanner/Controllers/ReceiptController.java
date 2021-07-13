package com.example.ReceiptScanner.Controllers;

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

@RestController
//@RequestMapping("/receipts")
public class ReceiptController {

    Logger log = LoggerFactory.getLogger(ReceiptController.class);

    @GetMapping("/receipts/test")
    public String debugs(){
        return "localhost is working";
    }

    @PostMapping("/receipts/scan")
    public void scanReceipt(@ModelAttribute MultipartFile image) throws IOException {

        log.info(ReceiptService.upload(image));
    }
}
