package com.springpub.demo.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpub.demo.dto.UserSignInResponse;
import com.springpub.demo.security.LoadUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.springpub.demo.security.Roles.CLIENT;
import static com.springpub.demo.security.Roles.MANAGER;
import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @SpyBean
    protected LoadUserDetailService loadUserDetailService;

    protected String signInAsClient() throws Exception{
        final User user = new User("vasya@gmail.com", passwordEncoder.encode("qwerty"),
                List.of(new SimpleGrantedAuthority("ROLE_" + CLIENT.name())));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("vasya@gmail.com");
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
        final User user = new User("vasya@gmail.com", passwordEncoder.encode("qwerty"),
                List.of(new SimpleGrantedAuthority("ROLE_" + MANAGER.name())));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("vasya@gmail.com");
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

}
