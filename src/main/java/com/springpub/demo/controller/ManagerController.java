package com.springpub.demo.controller;

import com.springpub.demo.service.ManagerService;
import lombok.Data;
import lombok.extern.java.Log;
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
