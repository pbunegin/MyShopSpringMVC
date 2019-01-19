package org.shop.controllers;

import org.shop.data.Product;
import org.shop.service.ProductService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
public class MyRestControlloer {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Product edit(@ModelAttribute Product product, @RequestParam("uploadImg") MultipartFile file){
        productService.createOrUpdateProduct(product);
        saveFile(product.getId(), file);
        return product;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String upload(@ModelAttribute Product product){
        productService.deleteProduct(product.getId());
        return "dxfghdf";

    }

    private void saveFile(long id, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Files.write(Paths.get("target/classes/static/prodImg/" + id + ".jpg"),
                        file.getBytes(), StandardOpenOption.CREATE);
                Files.write(Paths.get("resources/static/prodImg/" + id + ".jpg"),
                        file.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
        }
    }
}
