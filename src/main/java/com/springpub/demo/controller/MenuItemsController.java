package com.springpub.demo.controller;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.exception.NoSuchMenuItemException;
import com.springpub.demo.service.MenuItemsService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author valuados
 */
@Log
@Data
@RestController
@RequestMapping(value = "/menuItems", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class MenuItemsController {

    private final AuthenticationManager authenticationManager;

    private final MenuItemsService menuItemsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItem> getList(){
        return menuItemsService.getList();
    }

    @DeleteMapping(value = "/{menuItemId}")
    @ResponseStatus(HttpStatus.OK)
    public void getList(@PathVariable final Long menuItemId)
            throws NoSuchMenuItemException {
        menuItemsService.deleteMenuItem(menuItemId);
    }

}
