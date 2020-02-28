package com.springpub.demo.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.dto.UserSignInResponse;
import com.springpub.demo.entity.AuthInfoEntity;
import com.springpub.demo.entity.MenuItemEntity;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.repository.AuthInfoRepository;
import com.springpub.demo.repository.MenuItemRepository;
import com.springpub.demo.repository.UserRepository;
import com.springpub.demo.security.LoadUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

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


    /*protected MenuItemEntity createMenuItemInfo(){
        final MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setId(100L);
        menuItem.setTitle("Test");
        menuItem.setDescription("Test");
        menuItem.setPortion(300);
        menuItem.setBottleVolume(300);
        menuItem.setPortionPrice(5.50);
        menuItem.setBottlePrice(5.50);
        menuItem.setStrength(1.1);
        menuItemRepository.save(menuItem);
        return menuItem;
    }*/
}
