package com.springpub.demo.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.BDDMockito.willReturn;
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

        willReturn(findAllMenuItems()).given(menuItemRepository).findAll();
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

        willReturn(findAllMenuItems()).given(menuItemRepository).findAll();
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

        final Long id = 1L;
        final String token = signInAsManager();

        willReturn(Optional.of(getMenuItemEntity(
                1L,
                "Heineken",
                "То самое немецкое с пенкой",
                500,
                500,
                6.50,
                6.50,
                3.8)))
                .given(menuItemRepository)
                .findById(id);
        mockMvc.perform(delete("/menuItems/" + id).header("Authorization", token))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteManagerWrongMenuItem() throws Exception {

        final Long id = 1L;
        final String token = signInAsManager();

        willReturn(Optional.of(getMenuItemEntity(
                1L,
                "Heineken",
                "То самое немецкое с пенкой",
                500,
                500,
                6.50,
                6.50,
                3.8)))
                .given(menuItemRepository)
                .findById(id);
        mockMvc.perform(delete("/menuItems/99").header("Authorization", token))
                // then
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testDeleteClientMenuItem() throws Exception {

        final Long id = 1L;
        final String token = signInAsClient();

        willReturn(Optional.of(getMenuItemEntity(
                1L,
                "Heineken",
                "То самое немецкое с пенкой",
                500,
                500,
                6.50,
                6.50,
                3.8)))
                .given(menuItemRepository)
                .findById(id);
        mockMvc.perform(delete("/menuItems/1").header("Authorization", token))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAddManagerMenuItem() throws Exception {
        final String token = signInAsManager();
        final String title = "Heineken";
        final Long id = 1L;

        willReturn(0L).given(menuItemRepository).countAllByTitle(title);
        willReturn(1L)
                .given(menuItemRepository)
                .save(getMenuItemEntity(
                        null,
                        title,
                        "То самое немецкое с пенкой",
                        500,
                        500,
                        6.50,
                        6.50,
                        3.8)).getId();
        mockMvc.perform(post("/menuItems").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"title\" : \""+ title +"\",\n" +
                        "   \"description\" : \"То самое русское с пенкой\",\n" +
                        "   \"portion\" : 500,\n" +
                        "   \"bottleVolume\" : 500,\n" +
                        "   \"portionPrice\" : 6.50,\n" +
                        "   \"bottlePrice\" : 6.50,\n" +
                        "   \"strength\" : 3.8\n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : " + id + "\n" +
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

        final String title = "Heineken";

        willReturn(1L).given(menuItemRepository).countAllByTitle(title);

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
