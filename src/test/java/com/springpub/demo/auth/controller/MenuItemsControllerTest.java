package com.springpub.demo.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                        "    \"title\" : \"Heineken\",\n" +
                        "    \"description\" : \"То самое немецкое с пенкой\",\n" +
                        "    \"portion\" : 500, \n" +
                        "    \"bottleVolume\" : 500,\n" +
                        "    \"portionPrice\" : 6.50, \n" +
                        "    \"bottlePrice\" : 6.50, \n" +
                        "    \"strength\" : 3.8\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\" : 2, \n" +
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
                        "    \"title\" : \"Heineken\",\n" +
                        "    \"description\" : \"То самое немецкое с пенкой\",\n" +
                        "    \"portion\" : 500, \n" +
                        "    \"bottleVolume\" : 500,\n" +
                        "    \"portionPrice\" : 6.50, \n" +
                        "    \"bottlePrice\" : 6.50, \n" +
                        "    \"strength\" : 3.8\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\" : 2, \n" +
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
    public void testDeleteManagerMenuItem() throws Exception {

        final String token = signInAsManager();

        mockMvc.perform(delete("/menuItems/1").header("Authorization", token))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteManagerWrongMenuItem() throws Exception {

        final String token = signInAsManager();

        mockMvc.perform(delete("/menuItems/99").header("Authorization", token))
                // then
                .andExpect(status().isBadRequest());
    }

    /**
     * TODO access denied redirect and 403 Forbidden
     * @throws Exception
     */
    @Test
    public void testDeleteClientMenuItem() throws Exception {

        final String token = signInAsClient();

        mockMvc.perform(delete("/menuItems/2").header("Authorization", token))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAddManagerMenuItem() throws Exception {
        final String token = signInAsManager();

        mockMvc.perform(post("/menuItems").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"title\" : \"Жигулевское\",\n" +
                        "   \"description\" : \"То самое русское с пенкой\",\n" +
                        "   \"portion\" : 500,\n" +
                        "   \"bottleVolume\" : 500,\n" +
                        "   \"portionPrice\" : 4.50,\n" +
                        "   \"bottlePrice\" : 4.50,\n" +
                        "   \"strength\" : 3.2\n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 3\n" +
                        "}"));
    }

    @Test
    public void testAddClientMenuItem() throws Exception {
        final String token = signInAsClient();

        mockMvc.perform(post("/menuItems").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"title\" : \"Жигулевское2\",\n" +
                        "   \"description\" : \"То самое русское с пенкой 2\",\n" +
                        "   \"portion\" : 500,\n" +
                        "   \"bottleVolume\" : 500,\n" +
                        "   \"portionPrice\" : 4.50,\n" +
                        "   \"bottlePrice\" : 4.50,\n" +
                        "   \"strength\" : 3.2\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAddExistingMenuItem() throws Exception {
        final String token = signInAsManager();

        mockMvc.perform(post("/menuItems").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"title\" : \"Heineken\",\n" +
                        "    \"description\" : \"То самое немецкое с пенкой\",\n" +
                        "    \"portion\" : 500, \n" +
                        "    \"bottleVolume\" : 500,\n" +
                        "    \"portionPrice\" : 6.50, \n" +
                        "    \"bottlePrice\" : 6.50, \n" +
                        "    \"strength\" : 3.8\n" +
                        "}"))
                // then
                .andExpect(status().isBadRequest());
    }


}
