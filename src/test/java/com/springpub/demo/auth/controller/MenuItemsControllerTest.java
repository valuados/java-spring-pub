package com.springpub.demo.auth.controller;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
                        "    \"description\" : \"Водка Зубровка\",\n" +
                        "    \"portion\" : 50, \n" +
                        "    \"bottleVolume\" : 1000,\n" +
                        "    \"portionPrice\" : 5.00, \n" +
                        "    \"bottlePrice\" : 50.00, \n" +
                        "    \"strength\" : 40.0\n" +
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

    @Test
    public void testDeleteManagerMenuItem() throws Exception {

        final String token = signInAsManager();

        mockMvc.perform(delete("/menuItems/1").header("Authorization", token))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteManagerWrongMenuItem() throws Exception {

        final String token = signInAsManager();

        mockMvc.perform(delete("/menuItems/2").header("Authorization", token))
                // then
                .andExpect(status().isBadRequest());
    }

}
