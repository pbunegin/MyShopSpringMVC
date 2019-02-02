package org.shop.controllers;

import org.shop.data.Category;
import org.shop.data.Product;
import org.shop.service.CategoryService;
import org.shop.service.ProductService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@RestController
public class MyRestControlloer {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Product edit(@ModelAttribute Product product, @RequestParam("categoryName") String categoryName,
                        @RequestParam("uploadImg") MultipartFile file) {
        Category category = categoryService.getCategoryOrCreate(categoryName);
        product.setCategory(category);
        productService.createOrUpdateProduct(product);
        saveFile(product.getId(), file);
        return product;

    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public Map<String, Long> remove(@RequestBody Map<String, Long> request) {
        productService.deleteProduct(request.get("id"));
        return request;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Long> search(@RequestBody Map<String, String> request) {
        List<Long> result = productService.getIdProductsByParam(request);
        return result;
    }

    private void saveFile(Long id, MultipartFile file) {
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
