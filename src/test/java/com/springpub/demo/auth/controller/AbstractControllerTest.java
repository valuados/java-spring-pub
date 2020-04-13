package com.springpub.demo.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpub.demo.dto.Gender;
import com.springpub.demo.dto.UserSignInResponse;
import com.springpub.demo.entity.AuthInfoEntity;
import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.repository.AuthInfoRepository;
import com.springpub.demo.repository.MenuItemRepository;
import com.springpub.demo.repository.UserRepository;
import com.springpub.demo.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.springpub.demo.security.UserRole.CLIENT;
import static com.springpub.demo.security.UserRole.MANAGER;
import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @MockBean
    protected AuthInfoRepository authInfoRepository;
    @MockBean
    protected UserRepository userRepository;
    @MockBean
    protected MenuItemRepository menuItemRepository;


    protected String signInAsClient() throws Exception{
        final AuthInfoEntity authInfo = createClientAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin("vasya@gmail.com");

        final String response = mockMvc.perform(post("/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@gmail.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)))
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + objectMapper.readValue(response, UserSignInResponse.class).getToken();
    }

    protected String signInAsManager() throws Exception{
        final AuthInfoEntity authInfo = createManagerAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin("vasya@gmail.com");

        final String response = mockMvc.perform(post("/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@gmail.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)))
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + objectMapper.readValue(response, UserSignInResponse.class).getToken();
    }

    /*protected String createTestMenuItem() throws Exception{
        final String token = signInAsManager();
        final MenuItemEntity menuItemEntity = createMenuItemInfo();
        willReturn(Optional.of(menuItemEntity)).given(menuItemRepository).findById(menuItemEntity.getId());

        return token;
    }*/



    protected AuthInfoEntity createClientAuthInfo() {
        final UserEntity user = new UserEntity();
        user.setUserRole(CLIENT);
        user.setEmail("vasya@gmail.com");

        final AuthInfoEntity authInfo = new AuthInfoEntity();
        authInfo.setLogin(user.getEmail());
        authInfo.setPassword(passwordEncoder.encode("qwerty"));
        authInfo.setUser(user);
        return authInfo;
    }

    protected AuthInfoEntity createManagerAuthInfo() {
        final UserEntity user = new UserEntity();
        user.setUserRole(MANAGER);
        user.setEmail("vlad@gmail.com");

        final AuthInfoEntity authInfo = new AuthInfoEntity();
        authInfo.setLogin(user.getEmail());
        authInfo.setPassword(passwordEncoder.encode("qwerty"));
        authInfo.setUser(user);
        return authInfo;
    }

    protected UserEntity getUserEntity(Long id, String email) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setEmail(email);
        userEntity.setGender(Gender.MALE);
        userEntity.setFio("Krya Krya Krya");
        userEntity.setBirthDate(LocalDate.now());
        userEntity.setUserRole(UserRole.CLIENT);
        return userEntity;
    }

    protected List<MenuItemEntity> findAllMenuItems(){
        final MenuItemEntity menuItem1 = getMenuItemEntity(
                1L,
                "Heineken",
                "То самое немецкое с пенкой",
                500,
                500,
                6.50,
                6.50,
                3.8);

        final MenuItemEntity menuItem2 = getMenuItemEntity(
                2L,
                "Zubrowka",
                "Водка Зубровка",
                50, 1000,
                5.00,
                50.00,
                40.0);

        final List<MenuItemEntity> menuItems = new ArrayList<>();
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        return menuItems;
    }

    protected MenuItemEntity getMenuItemEntity(
            final Long id,
            final String title,
            final String description,
            final Integer portion,
            final Integer bottleVolume,
            final Double portionPrice,
            final Double bottlePrice,
            final Double strength
    ) {

        final MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setId(id);
        menuItem.setTitle(title);
        menuItem.setDescription(description);
        menuItem.setPortion(portion);
        menuItem.setBottleVolume(bottleVolume);
        menuItem.setPortionPrice(BigDecimal.valueOf(portionPrice));
        menuItem.setBottlePrice(BigDecimal.valueOf(bottlePrice));
        menuItem.setStrength(BigDecimal.valueOf(strength));
        menuItem.setOrderedItemEntity(null);
        return menuItem;
    }
}
