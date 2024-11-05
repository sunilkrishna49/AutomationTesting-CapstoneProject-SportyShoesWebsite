//package com.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.entity.User;
//import com.service.UserService;
//
//
////The @RestController annotation indicates that this is a REST API controller.
//
//
//@RestController
//
////The @RequestMapping("/api/products") annotation sets the base path for this API.
//@RequestMapping("/api/users")
//public class UserRestController {
//	
//	//Injecting UserService instance using @Autowired;
//	@Autowired
//	private UserService userService;
//	
//	
//	//Endpoint to retrieve all users
//	
//	//The @GetMapping method fetches all products when a GET request is made to /api/products.
//	@GetMapping
//	public List<User> getAllUsers() {
//		
//		return userService.getAllUsers();
//		
//	}
//	
//
//}
