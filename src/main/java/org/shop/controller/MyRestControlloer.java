package org.shop.controller;

import org.shop.data.Category;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.User;
import org.shop.service.CategoryService;
import org.shop.service.OrderService;
import org.shop.service.ProductService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

@RestController
public class MyRestControlloer {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderService orderService;

    @PutMapping("/edit")
    public Product edit(@ModelAttribute Product product, @RequestParam("categoryName") String categoryName,
                        @RequestParam("uploadImg") MultipartFile file) {
        Category category = categoryService.getCategoryOrCreate(categoryName);
        product.setCategory(category);
        productService.createOrUpdateProduct(product, file);
//        categoryService.clearCategory();
        return product;
    }

    @DeleteMapping("/delete")
    public List<Long> delete(@RequestBody List<Long> productId) {
        productId.forEach(id -> productService.deleteProduct(id));
//        categoryService.clearCategory();
        return productId;
    }

    @PostMapping("/search")
    public List<Long> search(@RequestBody Map<String, String> request) {
        return productService.getIdProductsByParam(request);
    }

    @PostMapping("/create_order")
    public Order createOrder(@RequestBody List<Long> productsId, HttpSession session){
        User user = userService.getUserByLogin(((User)session.getAttribute("user")).getLogin());
        Order order = new Order();
        Map<Product, Long> map = productService.getProducts(productsId)
                .stream().collect(Collectors.groupingBy(k->k,counting()));
        order.setProducts(map);
        order.setUser(user);
        orderService.createOrder(order);
        return order;
    }
}