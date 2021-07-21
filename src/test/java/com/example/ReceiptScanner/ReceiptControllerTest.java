package com.example.ReceiptScanner;

import com.example.ReceiptScanner.Controllers.ReceiptController;
import com.example.ReceiptScanner.Repositories.AccountRepository;
import com.example.ReceiptScanner.Repositories.ReceiptRepository;
import com.example.ReceiptScanner.Services.AccountService;
import com.example.ReceiptScanner.Services.ReceiptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptRepository repo;

    @MockBean
    private ReceiptService receiptService;

    @Test
    void testScanReceipt() throws Exception {
        String url = "/receipts/scan/{id}";

        mockMvc.perform( MockMvcRequestBuilders
                .put(url, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void testFindReceipt() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .get("/receipts/find/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testFindAllReceipts() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .get("/receipts/find")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
