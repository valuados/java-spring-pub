package com.springpub.demo.auth.controller;

import com.springpub.demo.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valuados
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class OrdersControllerTest extends AbstractControllerTest{

    @Test
    public void testClientAddOrder() throws Exception{
        final String token = signInAsClient();

        final User user = createClient();
        willReturn(user).given(userService).findByEmail("vasya@gmail.com");

        mockMvc.perform(post("/orders")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"orderedItems\":[\n" +
                        "      {\n" +
                        "         \"menuItem\":{\n" +
                        "            \"id\":1\n" +
                        "         },\n" +
                        "         \"volume\":1500\n" +
                        "      },\n" +
                        "      {\n" +
                        "         \"menuItem\":{\n" +
                        "            \"id\":2\n" +
                        "         },\n" +
                        "         \"volume\":150\n" +
                        "      }\n" +
                        "   ]\n" +
                        "}"))
                //then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1,\n" +
                        "  \"userId\" : 1,\n" +
                        "  \"totalPrice\" : 34.50,\n" +
                        "  \"status\" : \"NEW\",\n" +
                        "  \"orderedItems\":[\n" +
                        "      {\n" +
                        "         \"menuItem\":{\n" +
                        "            \"id\":1\n" +
                        "         },\n" +
                        "         \"volume\":1500,\n" +
                        "         \"totalPrice\": 19.50\n" +
                        "      },\n" +
                        "      {\n" +
                        "         \"menuItem\":{\n" +
                        "            \"id\":2\n" +
                        "         },\n" +
                        "         \"volume\":150,\n" +
                        "         \"totalPrice\": 15.00\n" +
                        "      }\n" +
                        "   ]\n" +
                        "}"));
    }

}
