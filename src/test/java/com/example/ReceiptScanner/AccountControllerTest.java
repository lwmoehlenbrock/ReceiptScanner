package com.example.ReceiptScanner;

import com.example.ReceiptScanner.Controllers.AccountController;
import com.example.ReceiptScanner.Model.Account;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.AccountRepository;
import com.example.ReceiptScanner.Repositories.UserRepository;
import com.example.ReceiptScanner.Services.AccountService;
import com.example.ReceiptScanner.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.ReceiptScanner.UserControllerTest.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository repo;

    @MockBean
    private AccountService accountService;

    @Test
    void testAddAccount() throws Exception {
        String url = "/accounts/addAccount";

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(asJsonString(new Account("Checking", "Primary Checking", 400)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testGetABalance() throws Exception {
        String url = "/accounts/getBalance";

        mockMvc.perform( MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateBalance() throws Exception {
        String url = "/accounts/updateBalance";

        mockMvc.perform( MockMvcRequestBuilders
                .put(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

}
