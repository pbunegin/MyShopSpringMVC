package org.shop.controllers;

import org.shop.data.Category;
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Product edit(@ModelAttribute Product product, @RequestParam String categoryName, @RequestParam("uploadImg") MultipartFile file){
        product.setImgUrl();
        Product productFromRepository = productService.getProductById(product.getId());
        if (productFromRepository != null){
            productFromRepository = product;
        } else {
            Category category = productService.getCategoryByName(categoryName);
            if (category != null){
                category.getProducts().add(product);
            } else {
                category = new Category(categoryName);
                category.getProducts().add(product);
                productService.createCategory(category);
            }
        }
        if (!file.isEmpty()) {
            try {
                Files.write(Paths.get("src\\main\\resources\\static\\prodImg\\" + product.getProductName().replaceAll("\\s", "_") + ".jpg"),
                        file.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
        }
        return product;

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file){

        return "dxfghdf";

    }
}
