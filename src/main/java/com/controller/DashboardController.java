//package com.controller;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import com.entity.Product;
//import com.entity.Purchase;
//import com.entity.User;
//import com.global.GlobalData;
//import com.service.ProductService;
//import com.service.PurchaseService;
//import com.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/dashboard")
//public class DashboardController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private PurchaseService purchaseService;
//
//    @GetMapping
//    public String dashboard(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.findByEmail(authentication.getName());
//
//        // Pass the cart items to the model
//        model.addAttribute("cart", GlobalData.cart);
//
//        if (userService.findByEmail(authentication.getName()).getRole().toString().equals("ADMIN")) {
//            // For Admin
//            List<Product> products = productService.getAllProducts();
//            model.addAttribute("products", products);
//            List<User> users = userService.getAllUsers();
//            model.addAttribute("users", users);
//            List<Purchase> purchases = purchaseService.getAllPurchases();
//            model.addAttribute("purchases", purchases);
//            return "/admin/admin-dashboard";
//        } else {
//            // For User
//            List<Purchase> purchases = purchaseService.getPurchasesByUser(currentUser);
//            model.addAttribute("purchases", purchases);
//            List<Product> products = productService.getAllProducts();
//            model.addAttribute("products", products);
//            return "user-dashboard";
//        }
//    }
//
//    // Add items to the cart
//    @PostMapping("/cart/add")
//    public String addToCart(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {
//        Product product = productService.getProductById(productId);
//        product.setQuantity(quantity);  // Set the desired quantity
//        GlobalData.cart.add(product);   // Add product to the cart
//        return "redirect:/dashboard";   // Redirect to dashboard to reflect changes
//    }
//
//    // View the cart
//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        model.addAttribute("cart", GlobalData.cart);  // Show the cart items
//        return "cart";  // Render cart page
//    }
//    
//    @GetMapping("/profile")
//    public String viewProfile(Model model) {
//    	
//    	//Get the authentication user
//    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    	User currentUser = userService.findByEmail(authentication.getName());
//    	
//    	//Add the user to the model to be accessed in the Thymleaf template
//    	model.addAttribute("user", currentUser);
//    	
//    	//Return the profile view
//    	return "profile";
//    	
//    }
//
//    // Checkout the cart
//    @PostMapping("/cart/checkout")
//    public String checkout(Authentication authentication) {
//        User currentUser = userService.findByEmail(authentication.getName());
//
//        for (Product product : GlobalData.cart) {
//            Purchase purchase = new Purchase();
//            purchase.setUser(currentUser);
//            purchase.setProduct(product);
//            purchase.setQuantity(product.getQuantity());
//            purchase.setPurchaseDate(LocalDateTime.now());
//
//            // Save the purchase and deduct the stock
//            purchaseService.savePurchase(purchase);
//            Product dbProduct = productService.getProductById(product.getId());
//            dbProduct.setQuantity(dbProduct.getQuantity() - product.getQuantity());
//            productService.addProduct(dbProduct);
//        }
//
//        // Clear the cart after checkout
//        GlobalData.cart.clear();
//
//        return "redirect:/dashboard";
//    }
//
//    // Remove an item from the cart
//    @GetMapping("/cart/remove/{index}")
//    public String removeFromCart(@PathVariable int index) {
//        GlobalData.cart.remove(index);  // Remove item from cart by index
//        return "redirect:/dashboard";  // Redirect back to the dashboard or cart
//    }
//    
//    //Show the edit profile form
//    @GetMapping("/profile/edit")
//    public String editProfile(Model model) {
//    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    	User currentUser = userService.findByEmail(authentication.getName());
//    	
//    	model.addAttribute("user", currentUser);
//    	return "edit-profile";
//    }
//    
//    @GetMapping("/profile/purchases")
//    public String viewPastPurchases(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.findByEmail(authentication.getName());
//
//        List<Purchase> purchases = purchaseService.getPurchasesByUser(currentUser);
//        model.addAttribute("purchases", purchases);
//        return "past-purchases";
//    }
//    
//    @GetMapping("/orderhistory")
//    public String viewOrderHistory(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.findByEmail(authentication.getName());
//
//        List<Purchase> purchases = purchaseService.getPurchasesByUser(currentUser);
//        model.addAttribute("purchases", purchases);
//        return "order-history";
//    }
//
//    
//    
//}


package com.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.entity.Product;
import com.entity.Purchase;
import com.entity.User;
import com.global.GlobalData;
import com.service.ProductService;
import com.service.PurchaseService;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByEmail(authentication.getName());
        
        model.addAttribute("cart", GlobalData.cart);

        if (isAdmin(currentUser)) {
            // Admin-specific data
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("purchases", purchaseService.getAllPurchases());
            return "/admin/admin-dashboard";
        } else {
            // User-specific data
            model.addAttribute("purchases", purchaseService.getPurchasesByUser(currentUser));
            model.addAttribute("products", productService.getAllProducts());
            return "user-dashboard";
        }
    }

    private boolean isAdmin(User user) {
        return "ADMIN".equals(user.getRole().toString());
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {
        Product product = productService.getProductById(productId);
        product.setQuantity(quantity);  
        GlobalData.cart.add(product);   
        return "redirect:/dashboard";   
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", GlobalData.cart);  
        return "cart";  
    }

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/cart/checkout")
    public String checkout() {
        User currentUser = getCurrentUser();

        for (Product product : GlobalData.cart) {
            Purchase purchase = new Purchase();
            purchase.setUser(currentUser);
            purchase.setProduct(product);
            purchase.setQuantity(product.getQuantity());
            purchase.setPurchaseDate(LocalDateTime.now());

            purchaseService.savePurchase(purchase);
            Product dbProduct = productService.getProductById(product.getId());
            dbProduct.setQuantity(dbProduct.getQuantity() - product.getQuantity());
            productService.addProduct(dbProduct);
        }

        GlobalData.cart.clear();
        return "redirect:/dashboard";
    }

    @GetMapping("/cart/remove/{index}")
    public String removeFromCart(@PathVariable int index) {
        GlobalData.cart.remove(index);  
        return "redirect:/dashboard";  
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("user", currentUser);
        return "edit-profile";
    }

    @GetMapping("/profile/purchases")
    public String viewPastPurchases(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("purchases", purchaseService.getPurchasesByUser(currentUser));
        return "past-purchases";
    }

    @GetMapping("/orderhistory")
    public String viewOrderHistory(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("purchases", purchaseService.getPurchasesByUser(currentUser));
        return "order-history";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(authentication.getName());
    }
}
