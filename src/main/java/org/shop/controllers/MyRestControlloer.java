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
//    produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product edit(@ModelAttribute Product product, @RequestParam("uploadImg") MultipartFile file){
        productService.createOrUpdateProduct(product);
        saveFile(product.getId(), file);
        return product;

    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public long remove(@RequestParam("id") long id){
        productService.deleteProduct(id);
        return id;
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
