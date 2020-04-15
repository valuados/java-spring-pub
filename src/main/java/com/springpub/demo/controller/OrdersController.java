package com.springpub.demo.controller;

import com.springpub.demo.dto.Order;
import com.springpub.demo.dto.OrderedItem;
import com.springpub.demo.service.OrdersService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.List;


/**
 * @author valuados
 */

@Log
@Data
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    private final AuthenticationManager authenticationManager;

    private final OrdersService ordersService;


    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Order add(@RequestBody Order request, final Authentication authentication){

        return ordersService.create(authentication.getName());

    }
    //@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(final Authentication authentication){
        return ordersService.create(authentication.getName());
    }
}
