package com.springpub.demo.auth.controller;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.mapper.MenuItemMapper;
import com.springpub.demo.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */

public class MenuItemsControllerTest extends AbstractControllerTest{

//    @MockBean
//    private MenuItemRepository menuItemRepository;
    @SpyBean
    private MenuItemMapper menuItemMapper;

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

    @Test
    public void testChangePortion() throws Exception {
        // given
        final String token = signInAsManager();
        Long id = 1L;
        MenuItem menuItem = objectMapper
            .readValue("{\n" +
                       "    \"id\": 1," +
                       "    \"title\" : \"Heineken\",\n" +
                       "    \"description\" : \"То самое немецкое с пенкой\",\n" +
                       "    \"portion\" : 400, \n" +
                       "    \"bottleVolume\" : 400,\n" +
                       "    \"portionPrice\" : 6.50, \n" +
                       "    \"bottlePrice\" : 6.50, \n" +
                       "    \"strength\" : 3.8\n" +
                       "}", MenuItem.class);

        MenuItemEntity menuItemEntity = menuItemMapper.sourceToDestination(menuItem);

        willReturn(Optional.of(menuItemEntity)).given(menuItemRepository).findById(id);
        willReturn(menuItemMapper.sourceToDestination(menuItem)).given(menuItemRepository)
            .save(any(MenuItemEntity.class));
        // when
        mockMvc.perform(patch("/menuItems/" + id).header("Authorization", token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                     "    \"portion\" : 500 \n" +
                                     "}"))
            // then
            .andExpect(status().isOk());
        verify(menuItemRepository, times(1)).findById(id);
        verify(menuItemRepository, times(1)).save(any(MenuItemEntity.class));
    }

    @Test
    public void testChangePortion_NoSuchMenuItem() throws Exception {
        // given
        final String token = signInAsManager();
        final Long id = 1L;

        willReturn(Optional.empty()).given(menuItemRepository).findById(id);
        // when
        mockMvc.perform(patch("/menuItems/" + id).header("Authorization", token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                     "    \"portion\" : 500 \n" +
                                     "}"))
            // then
            .andExpect(status().isBadRequest());
        verify(menuItemRepository, times(1)).findById(id);
    }
}
