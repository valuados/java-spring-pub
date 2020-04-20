package com.springpub.demo.controller;

import com.springpub.demo.dto.MenuItemDTO;
import com.springpub.demo.exception.ItemAlreadyExistsException;
import com.springpub.demo.exception.MenuItemNotFoundException;
import com.springpub.demo.service.MenuItemsService;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author valuados
 */

@Api()
@Log
@Data
@RestController
@RequestMapping(value = "/menuItems")
public class MenuItemsController {

    private final AuthenticationManager authenticationManager;

    private final MenuItemsService menuItemsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> getList(){
        return menuItemsService.getList();
    }

    @DeleteMapping(value = "/{menuItemId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenuItem(@PathVariable final Long menuItemId)
            throws MenuItemNotFoundException {
        menuItemsService.deleteMenuItem(menuItemId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Long> addMenuItem(@RequestBody final MenuItemDTO request)
            throws ItemAlreadyExistsException {
        return menuItemsService.addMenuItem(request);
    }

}
