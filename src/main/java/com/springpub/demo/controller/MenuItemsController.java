package com.springpub.demo.controller;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.exception.ItemAlreadyExsists;
import com.springpub.demo.exception.NoSuchMenuItemException;
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
    public List<MenuItem> getList(){
        return menuItemsService.getList();
    }

    @DeleteMapping(value = "/{menuItemId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenuItem(@PathVariable final Long menuItemId)
            throws NoSuchMenuItemException {
        menuItemsService.deleteMenuItem(menuItemId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Long> addMenuItem(@RequestBody final MenuItem request)
            throws ItemAlreadyExsists {
        return menuItemsService.addMenuItem(request);
    }

}
