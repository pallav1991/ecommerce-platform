package com.ecommerce.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductRestController {
    @GetMapping("/greet")
    public String welcome(){
        return "Hello";
    }
}
