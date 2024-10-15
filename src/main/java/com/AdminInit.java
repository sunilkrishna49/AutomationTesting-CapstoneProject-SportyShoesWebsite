package com;

import com.entity.Role;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInit implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();     
        
        User u = userService.findByEmail("admin@admin.com");
        if(u==null) {	        	
	        admin.setEmail("admin@admin.com");         
	        admin.setPassword("a"); 
	        admin.setName("Admin");
	        admin.setSurname("User");
	        userService.createAdmin(admin);
        }
    }
}