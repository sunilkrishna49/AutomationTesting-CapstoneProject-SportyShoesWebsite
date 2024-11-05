//package com.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.entity.Product;
//import com.service.ProductService;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductRestController {
//	
//	
//	//Injecting ProductService instance using @Autowired;
//	@Autowired
//	private ProductService productService;
//	
//	//Endpoints to retrieve all products
//	
//	@GetMapping("/api/products")
//	public List<Product> getAllProducts() {
//		return productService.getAllProducts();
//		
//	}
//	
//	
//	
//	
//
//}
