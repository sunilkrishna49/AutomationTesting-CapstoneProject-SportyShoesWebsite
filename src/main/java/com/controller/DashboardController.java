package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.ProductService;
import com.service.UserService;
import com.entity.Product;
import com.entity.User;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
    private UserService userService;
	  @Autowired
	private ProductService productService;
	  
    @GetMapping
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.err.println(authentication);
        System.err.println(authentication.getName());
        if(userService.findByEmail(authentication.getName()).getRole().toString() == "ADMIN") {
        	List<Product> products = productService.getAllProducts(); 
            model.addAttribute("products", products);   
        	List<User> users = userService.getAllUsers(); 
            model.addAttribute("users", users); 
        	return "/admin/admin-dashboard";
        }else {
        	List<Product> products = productService.getAllProducts(); 
            model.addAttribute("products", products); 
        	return "user-dashboard";
        }
    }
    

    
}
