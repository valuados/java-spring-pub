package com.springpub.demo.auth.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class OrdersControllerTest extends AbstractControllerTest{

    @Test
    public void testClientAddOrder() throws Exception{
        final String token = signInAsClient();

        mockMvc.perform(post("/orders")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"orderedItems\": [\n" +
                        "      {\"menuItemId\": \"1\", \"volume\": 1550, \"totalPrice\" : 105.00},\n" +
                        "      {\"menuItemId\": \"2\", \"volume\": 150, \"totalPrice\" : 45.00}\n" +
                        "                ]\n" +
                        "}"))
                //then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1,\n" +
                        "  \"userId\" : 1,\n" +
                        "  \"totalPrice\" : 150.00,\n" +
                        "  \"status\" : \"NEW\",\n" +
                        //TODO remove details and check for valid
                        "   \"orderedItems\": [\n" +
                        "      {\"menuItemId\": 1, \"volume\": 1550, \"totalPrice\" : 105.00},\n" +
                        "      {\"menuItemId\": 2, \"volume\": 150, \"totalPrice\" : 45.00}\n" +
                        "                ]\n" +
                        "}"));
    }
}
