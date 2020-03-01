package com.springpub.demo.auth.controller.unit;

import com.springpub.demo.auth.controller.AbstractControllerTest;
import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MenuItemsControllerUnitTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @Test
    public void testChangeTitleMenuItem() throws Exception {
        //given
        final MenuItemEntity menuItemEntity = new MenuItemEntity();
        menuItemEntity.setId(1L);
        final Optional<MenuItemEntity> requiredMenuItem = Optional.of(menuItemEntity);
        willReturn(requiredMenuItem).given(menuItemRepository).findById(any(Long.class));
        willReturn(menuItemEntity).given(menuItemRepository).save(any(MenuItemEntity.class));
        final String token = signInAsManager();
        //when
        mockMvc.perform(patch("/menuItems/1 ").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"title\" : \"Новый бровар\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
        verify(menuItemRepository, times(1)).findById(any(Long.class));
        verify(menuItemRepository, times(1)).save(any(MenuItemEntity.class));
    }

    @Test
    public void testChangeTitleMenuItemThrowsNoSuchMenuItemException() throws Exception {
        //given
        final MenuItemEntity menuItemEntity = new MenuItemEntity();
        menuItemEntity.setId(1L);
        willReturn(Optional.empty()).given(menuItemRepository).findById(any(Long.class));
        willReturn(menuItemEntity).given(menuItemRepository).save(any(MenuItemEntity.class));
        final String token = signInAsManager();
        //when
        mockMvc.perform(patch("/menuItems/1 ").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"title\" : \"Новый бровар\"\n" +
                        "}"))
                // then
                .andExpect(status().isBadRequest());
        verify(menuItemRepository, times(1)).findById(any(Long.class));
        verify(menuItemRepository, times(0)).save(any(MenuItemEntity.class));
    }
}
