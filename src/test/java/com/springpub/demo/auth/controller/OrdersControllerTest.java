package com.springpub.demo.auth.controller;

import com.springpub.demo.dto.Gender;
import com.springpub.demo.entity.UserEntity;
import com.springpub.demo.security.UserRole;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author valuados
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class OrdersControllerTest extends AbstractControllerTest{

    @Test
    public void testClientAddEmptyOrder() throws Exception{
        final String token = signInAsClient();
        final Long id = 1L;
        final String email = "vasya@gmail.com";
        willReturn(Optional.of(getUserEntity(id, email))).given(userRepository).findByEmail(email);
        mockMvc.perform(post("/orders/create")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                //then
                .andExpect(status().isCreated())
                //TODO prepare date independent tests
                .andExpect(content().json("{" +
                        "\"id\":1," +
                        "\"userId\":1," +
                        "\"totalPrice\":0," +
                        "\"status\":\"NEW\"," +
                        //"\"creationDate\":\"2020-04-13@16:34:12\"," +
                        "\"updateDate\":null," +
                        "\"paidDate\":null," +
                        "\"orderedItemRequests\":null" +
                        "}"));
    }

    public void testClientAddOrder() throws Exception{
        final String token = signInAsClient();

        mockMvc.perform(post("/orders/add")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"orderedItemRequest\": [\n" +
                        "      {\"menuItemId\": \"1\", \"volume\": 1500},\n" +
                        "      {\"menuItemId\": \"2\", \"volume\": 150}\n" +
                        "                ]\n" +
                        "}"))
                //then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1,\n" +
                        "  \"userId\" : 1,\n" +
                        "  \"totalPrice\" : 64.50,\n" +
                        "  \"status\" : \"NEW\",\n" +
                        //TODO remove details and check for valid
                        "   \"orderedItems\": [\n" +
                        "      {\"menuItemId\": 1, \"volume\": 1550, \"totalPrice\" : 19.50},\n" +
                        "      {\"menuItemId\": 2, \"volume\": 150, \"totalPrice\" : 45.00}\n" +
                        "                ]\n" +
                        "}"));
    }
}
