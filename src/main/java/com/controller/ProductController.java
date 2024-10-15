package com.controller;

import com.entity.Product;
import com.entity.User;
import com.service.ProductService;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
	@Autowired
    private UserService userService;
	
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products); 
        return "redirect:/dashboard"; 
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/dashboard"; 
    }
    @GetMapping("edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id); 
        System.err.println("bura1");
        model.addAttribute("product", product);        
        return "editproduct"; 
    }
    
    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
    	   System.err.println("bura2");
        productService.updateProduct(id, product); 
        return "redirect:/dashboard"; 
    }
    
    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id ) {
        productService.deleteProduct(id);
        return "redirect:/dashboard"; 
    }
    
 
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public String searchProducts(@RequestParam String query, Model model) {
    	System.err.println("aramaya geldin");
        List<Product> productsearch = productService.searchProductsByName(query); 
        System.err.println("bulunan"+productsearch);
    	List<Product> products = productService.getAllProducts(); 
        model.addAttribute("products", products);  
        model.addAttribute("productsearch", productsearch); 
    	List<User> users = userService.getAllUsers(); 
        model.addAttribute("users", users);  
        return "/admin/admin-dashboard"; 
    }

}
