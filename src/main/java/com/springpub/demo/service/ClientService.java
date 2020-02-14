package com.springpub.demo.service;

import com.springpub.demo.dto.ClientSignUpRequest;
import com.springpub.demo.dto.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author valuados
 */

@Service
public class ClientService {

    public String signUp(final ClientSignUpRequest request) {
        return "{\"id\":1}";
    }


    public List<MenuItem> getList(){
        return List.of(MenuItem.builder()
                .id(1L)
                .title("Zubrowka")
                .portion(50)
                .bottleVolume(1000)
                .portionPrice(5.0)
                .bottlePrice(50.0)
                .strength(40.0)
                .description("Водка Зубровка")
                .build());
    }
}
