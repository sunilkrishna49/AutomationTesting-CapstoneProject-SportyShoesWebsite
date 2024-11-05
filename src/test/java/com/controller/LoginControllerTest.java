package com.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.entity.User;
import com.service.UserService;

public class LoginControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private LoginController loginController;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		 // Set up view resolver for redirect or forward requests
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController)
						.setViewResolvers(viewResolver)
						.build();
		
	}
	
	@Test
	public void signupForm_ShouldReturnSignupPage() throws Exception {
		
		mockMvc.perform(get("/signup"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(view().name("signup"));
		
		
	}
	
	@Test
	public void registerUser_ShouldRedirectToLoginWithMessage() throws Exception {
		
		//Simulate a user entity for registration
		User user = new User();
		user.setEmail("testUser@example.com");
		user.setPassword("testPassword");
		
		mockMvc.perform(post("/signup")
				.flashAttr("user", user))  //Attaches the user object to the request as a flash attribute. In a real scenario, this data would come from form submission in a signup form. Flash attributes allow the controller to access the data as if it were submitted from the form.
				.andExpect(status().is3xxRedirection()) //Asserts that the response status is a 3xx redirection, which means the request is being redirected.
				.andExpect(redirectedUrl("/login"))
				.andExpect(flash().attribute("successMessage", "User created successfully!")); //Checks that the controller has added a flash attribute with a key of "successMessage" and a value of "User created successfully!"
		
		verify(userService).registerUser(user);  //Asserts that the registerUser() method in the UserService was called with the provided user object. This ensures that the controller actually attempted to register the user with the information it received.
	}
	
	@Test
	public void login_ShouldReturnLoginPage() throws Exception {
		
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
		
	}
	
	@Test
	public void deflogin_ShouldReturnLoginPage() throws Exception {
		
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}

}
