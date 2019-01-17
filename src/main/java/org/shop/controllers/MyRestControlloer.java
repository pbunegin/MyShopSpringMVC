package org.shop.controllers;

import org.shop.service.ProductService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestControlloer {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping("/edit")
    public String edit(@RequestBody Object str){
        System.out.println(str);
        return str.toString();

    }
}
