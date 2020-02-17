package com.springpub.demo.controller;

import com.springpub.demo.dto.MenuItem;
import com.springpub.demo.service.MenuItemsService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author valuados
 */
@Log
@Data
@RestController
@RequestMapping

public class MenuItemsController {

    private final MenuItemsService menuItemsService;

    @GetMapping(value = "/menuItems")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItem> getList(){
        return menuItemsService.getList();
    }

    @DeleteMapping(value = "/menuItems/{menuItemId}")
    @ResponseStatus(HttpStatus.OK)
    public void getList(@PathVariable final Long menuItemId){
        menuItemsService.deleteMenuItem();
    }

}
