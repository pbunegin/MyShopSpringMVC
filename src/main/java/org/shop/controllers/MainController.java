package org.shop.controllers;

import org.shop.data.User;
import org.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class MainController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String checkLogin(@ModelAttribute User user,Model model) {
        if (user == null)
            return "redirect:login";
        model.addAttribute("userName", user.getFirstName()+" "+user.getLastName());
        return "index";
    }

//    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
//    public String index(Model model) {
//        model.addAttribute("products", productService.getCategories());
//        return "index";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
//        model.addAttribute("products", productService.getCategories());
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
//        model.addAttribute("products", productService.getCategories());
        return "registration";
    }

}