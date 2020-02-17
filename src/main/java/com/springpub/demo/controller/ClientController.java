package com.springpub.demo.controller;


import com.springpub.demo.service.ClientService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author valuados
 */
@Log
@Data
@RestController
@RequestMapping("/client")

public class ClientController {

    private final ClientService clientService;

}
