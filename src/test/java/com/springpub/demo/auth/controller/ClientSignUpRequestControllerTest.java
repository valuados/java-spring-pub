package com.springpub.demo.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class ClientSignUpRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testClientSignUpIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/client/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@gmail.com\",\n" +
                        "  \"password\" : \"qwerty\",\n" +
                        "  \"fio\" : \"Пупкин Василий Иванович\",\n" +
                        "  \"phoneNumber\" : \"+8-800-555-35-35\",\n"+
                        "  \"gender\" : \"MALE\", \n" +
                        "  \"birthDate\" : \"19.01.1995\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1}"));
    }

    @Test
    public void testClientSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/client/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@gmail.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testGetClientMenu() throws Exception {
        // given
        // when
        mockMvc.perform(get("/client/menu"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"title\" : \"Zubrowka\",\n" +
                        "    \"portion\" : 50, \n" +
                        "    \"bottleVolume\" : 1000,\n" +
                        "    \"portionPrice\" : 5.0, \n" +
                        "    \"bottlePrice\" : 50.0, \n" +
                        "    \"strength\" : 40.0,\n" +
                        "    \"description\" : \"Водка Зубровка\"\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testClientCreateOrder() throws Exception {
        // given
        // when
        mockMvc.perform(post("/client/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"waiterId\" : \"1\",\n" +
                        "   \"itemsList\": [\n" +
                        "      {\"menuItemId\": \"1\", \"volume\": \"1550\", \"volumeAmount\" : \"105\"}\n" +
                        "      {\"menuItemId\": \"2\", \"volume\": \"150\", \"volumeAmount\" : \"45\"}\n" +
                        "                ],\n" +
                        "   \"totalAmount\" : \"150\"\n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testClientCreatePayment() throws Exception {
        // given
        // when
        mockMvc.perform(get("/client/payment")
                .header("orderId", (int)1)
                .header("amount", (double)50)
                .header("tips", (double)10))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"oddMoney\" : \"5\"\n" +
                        "}"));
    }
}
