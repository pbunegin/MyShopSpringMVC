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
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyRestControlloer {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Map<String, Object> edit(@ModelAttribute Product product, @RequestParam String categoryName,
                                    @RequestParam("uploadImg") MultipartFile file){
        productService.createOrUpdateProduct(categoryName, product);
        saveFile(product.getProductName(), file);
        Map<String, Object> result =  new HashMap<>();
        result.put("categoryName", categoryName);
        result.put("product", product);
        return result;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String upload(@ModelAttribute Product product){
        productService.deleteProduct(product.getId());
        return "dxfghdf";

    }

    private void saveFile(String productName, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Files.write(Paths.get("target/classes/static/prodImg/" + productName.replaceAll("\\s", "_") + ".jpg"),
                        file.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
        }
    }
}
