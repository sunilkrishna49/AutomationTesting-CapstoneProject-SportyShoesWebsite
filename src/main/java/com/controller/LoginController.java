package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; 
    }

    @PostMapping("/signup")
    public String signup(User user) {
        userService.registerUser(user);
        return "redirect:/login"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
    @GetMapping("")
    public String deflogin() {
        return "login"; 
    }
}