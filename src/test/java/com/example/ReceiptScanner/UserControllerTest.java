package com.example.ReceiptScanner;

import com.example.ReceiptScanner.Controllers.UserController;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.UserRepository;
import com.example.ReceiptScanner.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repo;

    @MockBean
    private UserService userService;

    @Test
    void createUser() throws Exception {
        String url = "/user/";

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(asJsonString(new User("Adriaan", 300, 4000)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .get("/user/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetSavingsGoal() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .get("/user/getGoal")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateSavingsGoal() throws Exception {

            mockMvc.perform( MockMvcRequestBuilders
                    .put("/updateSavingsGoal")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void testSetBudget() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/updateBudget")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
