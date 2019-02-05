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

    @PostMapping("/create_order")
    public void createOrder(@RequestBody Set<Long> request, HttpSession session){
        User user = userService.getUserByLogin(((User)session.getAttribute("user")).getLogin());
        List<Product> products = productService.getProducts(new ArrayList<>(request));
        Order order = new Order();
        order.setProducts(products);
//        Map<Product,Integer> map = new HashMap<>();
//        for (Product product: products){
//            map.put(product,7);
//        }
//        order.setProducts(map);
        order.setUser(user);
        orderService.createOrder(order);
    }
}
