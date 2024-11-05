package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.registerUser(user);  // Register the user through the UserService
        redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");  // Add success message
        return "redirect:/login";  // Redirect to the login page
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