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

}
