package com.ecommerce.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
    @GetMapping("/")
    public String welcome(){
        return "Hello";
    }
}
