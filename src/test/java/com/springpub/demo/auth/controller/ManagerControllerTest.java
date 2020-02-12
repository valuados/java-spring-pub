package com.springpub.demo.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testManagerCreateMenuItem() throws Exception {
        mockMvc.perform(post("/manager/menu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"title\" : \"Zubrowka\",\n" +
                        "    \"portion\" : 50, \n" +
                        "    \"bottleVolume\" : 1000,\n" +
                        "    \"portionPrice\" : 5, \n" +
                        "    \"bottlePrice\" : 50, \n" +
                        "    \"strength\" : 40,\n" +
                        "    \"description\" : \"Водка Зубровка\"\n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testManagerDeleteMenuItem() throws Exception {
        mockMvc.perform(delete("manager/menu/delete/{itemId}"))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void testManagerUpdatePrice() throws Exception {
        mockMvc.perform(put("manager/menu/update")
                .header("menuItemId", (int)1)
                .header("price", (double)8.30))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\" : 1\n" +
                        "}"));
    }
}
