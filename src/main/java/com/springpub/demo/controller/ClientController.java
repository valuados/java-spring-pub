package com.springpub.demo.controller;


import com.springpub.demo.dto.ClientSignUpRequest;
import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.service.ClientService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author valuados
 */
@Log
@Data
@RestController
@RequestMapping("/client")

public class ClientController {

    private final ClientService clientService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String singUp(@RequestBody final ClientSignUpRequest request) {
        return clientService.signUp(request);
    }

    @GetMapping(value = "/menu")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItem> getList(){
        return clientService.getList();
    }
}
