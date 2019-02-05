package org.shop.controller;

import org.shop.data.Category;
import org.shop.data.Product;
import org.shop.service.CategoryService;
import org.shop.service.ProductService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/edit")
    public Product edit(@ModelAttribute Product product, @RequestParam("categoryName") String categoryName,
                        @RequestParam("uploadImg") MultipartFile file) {
        Category category = categoryService.getCategoryOrCreate(categoryName);
        product.setCategory(category);
        productService.createOrUpdateProduct(product, file);
        return product;
    }

    @DeleteMapping("/delete")
    public Map<String, Long> delete(@RequestBody Map<String, Long> request) {
        Category category = productService.getProductById(request.get("id")).getCategory();
        productService.deleteProduct(request.get("id"));
        if (category.getProducts().isEmpty()){
            categoryService.deleteCategory(category.getId());
        }
        return request;
    }

    @PostMapping("/search")
    public List<Long> search(@RequestBody Map<String, String> request) {
        List<Long> result = productService.getIdProductsByParam(request);
        return result;
    }
}
