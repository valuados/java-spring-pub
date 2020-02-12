package com.springpub.demo.controller;

import com.springpub.demo.dto.MenuItemAddRequest;
import com.springpub.demo.dto.MenuItemDeleteRequest;
import com.springpub.demo.dto.MenuItemUpdatePriceRequest;
import com.springpub.demo.service.ManagerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author valuados
 */

@Log
@Data
@RestController
@RequestMapping("/manager")

public class ManagerController {

    final private ManagerService managerService;

    @PostMapping(value = "/menu/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String addMenuItem(@RequestBody final MenuItemAddRequest request) {
        /*log.info(String.format("Created the next item: \n(%d)\n", request));*/
        return managerService.addMenuItem(request);
    }

    @DeleteMapping(value = "menu/delete/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable("itemId") Long id,
                           @RequestBody final MenuItemDeleteRequest request){
        managerService.deleteMenuItem(id, request);
        log.info(String.format("Deleting the item with ID: (%d).", id));
    }

    @PutMapping(value = "/menu/update")
    public String updatePrice(@RequestHeader("menuItemId") Long id,
                              @RequestHeader("price") double price,
                              @RequestBody final MenuItemUpdatePriceRequest request){
        return managerService.updatePrice(id, price, request);
    }

}
