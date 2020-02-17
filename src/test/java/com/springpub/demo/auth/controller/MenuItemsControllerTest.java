package com.springpub.demo.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */

public class MenuItemsControllerTest extends AbstractControllerTest{

    @Test
    public void testGetClientMenuItem() throws Exception {

        final String token = signInAsClient();

        mockMvc.perform(get("/menuItems").header("Authorization", token))
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
    public void testGetManagerMenuItem() throws Exception {

        final String token = signInAsManager();

        mockMvc.perform(get("/menuItems").header("Authorization", token))
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

}
