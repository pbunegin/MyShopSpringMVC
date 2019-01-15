package org.shop.controllers;

import org.shop.data.User;
import org.shop.service.ProductService;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String checkLogin(HttpSession session, Model model) {
        String fullName = (String) session.getAttribute("user");
        if (fullName == null)
            return "redirect:login";
        model.addAttribute("products", productService.getCategories());
        return "index";
    }


    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String login,
                        @RequestParam(required = false) String password, HttpSession session) {
        if (login != null || password != null) {
            User user = userService.getUserByLogin(login);
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("user", user.getFirstName() + " " + user.getLastName());
                session.removeAttribute("errorLoginPassword");
                return "redirect:index";
            } else {
                session.setAttribute("errorLoginPassword", "Неверный логин/пароль");
            }
        }
        return "login";
    }

    @RequestMapping(value = "/registration")
    public String registration(@RequestParam Map<String, String> parameters, HttpSession session) {
        if (parameters.size() == 0) {
            return "registration";
        }
        User user = new User();
        user.setFirstName(parameters.get("firstName"));
        user.setLastName(parameters.get("lastName"));
        user.setLogin(parameters.get("login"));
        user.setPassword(parameters.get("password"));
        userService.registerUser(user);
        session.setAttribute("user", user.getFirstName() + " " + user.getLastName());
        return "redirect:index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:login";
    }

}