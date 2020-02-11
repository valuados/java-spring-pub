package com.springpub.demo.service;

import com.springpub.demo.dto.ClientSignUpRequest;
import com.springpub.demo.dto.MenuItemAddRequest;
import com.springpub.demo.dto.MenuItemDeleteRequest;
import com.springpub.demo.dto.MenuItemUpdatePriceRequest;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

/**
 * @author valuados
 */

@Log
@Service
public class ManagerService {

    public String addMenuItem(final MenuItemAddRequest request) {
        return "{\n" +
                "  \"id\" : 1\n" +
                "}";
    }
    public void deleteMenuItem(Long id, final MenuItemDeleteRequest request){

    }

    public String updatePrice(Long id, double price, final MenuItemUpdatePriceRequest request){
        return String.format("{\n" +
                "    \"id\" : {0}}\n" +
                "}", id);
    }
}
