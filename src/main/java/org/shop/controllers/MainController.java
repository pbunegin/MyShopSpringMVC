package org.shop.controllers;

import org.shop.data.Product;
import org.shop.data.User;
import org.shop.service.CategoryService;
import org.shop.service.ProductService;
import org.shop.service.RoleService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:login";
        Map<String, List<Product>> products = createMap();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String login,
                        @RequestParam(required = false) String password, HttpSession session) {
        if (login != null || password != null) {
            User user = userService.getUserByLogin(login);
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                session.removeAttribute("errorLoginPassword");
                return "redirect:index";
            } else {
                session.setAttribute("errorLoginPassword", "Неверный логин/пароль");
            }
        }
        return "login";
    }

    @RequestMapping(value = "/registration")
    public String registration(@ModelAttribute User user, HttpSession session) {
        if (user.getLogin() == null) {
            return "registration";
        }
        if (user.getRole()==null){
            user.setRole(roleService.getRoleById(2L));
        }
        userService.registerUser(user);
        session.setAttribute("user", user);
        return "redirect:index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    private Map<String, List<Product>> createMap() {
        Map<String, List<Product>> result = new HashMap<>();
        for (Product product : productService.getProducts()) {
            String categoryName = product.getCategory().getCategoryName();
            if (result.get(categoryName) == null){
                result.put(categoryName, new ArrayList<>());
            }
            result.get(categoryName).add(product);
        }
        return result;
    }
}