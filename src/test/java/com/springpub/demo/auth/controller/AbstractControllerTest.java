package com.springpub.demo.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpub.demo.dto.User;
import com.springpub.demo.dto.Gender;
import com.springpub.demo.dto.UserSignInResponse;
import com.springpub.demo.entity.AuthInfoEntity;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.repository.AuthInfoRepository;
import com.springpub.demo.repository.UserRepository;
import com.springpub.demo.service.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    protected UserService userService;
    /*@MockBean
    protected MenuItemRepository menuItemRepository;*/


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

    protected UserEntity createUserEntity() {
        final UserEntity user = new UserEntity();
        user.setUserRole(CLIENT);
        user.setEmail("vasya@gmail.com");
        user.setFio("NVA");
        user.setGender(Gender.MALE);
        user.setId(1L);

        return user;
    }

    protected User createClient() {
        final User user = new User();
        user.setId(1L);
        user.setFio("VNA");
        user.setGender(Gender.MALE);
        user.setMail("vasya@gmail.com");
        user.setPassword("password");
        user.setPhoneNumber("123321");

        return user;
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

}
