package com.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.entity.Product;
import com.entity.Purchase;
import com.entity.Role;
import com.entity.User;
import com.service.ProductService;
import com.service.PurchaseService;
import com.service.UserService;

public class DashboardControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	@Mock
	private ProductService productService;
	@Mock
	private PurchaseService purchaseService;
	@Mock
	private SecurityContext securityContext;
	@Mock
	private Authentication authentication;
	
	@InjectMocks
	private DashboardController dashboardController;
	
	private User mockUser;
	
	@BeforeEach
	public void setup() {
		
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(dashboardController)
				 .setViewResolvers(new InternalResourceViewResolver("/WEB-INF/views/", ".jsp"))
				 .build();
		
		mockUser = new User();
		mockUser.setEmail("test1user@example.com");
		mockUser.setPassword("test1Password");
		mockUser.setRole(Role.USER);
		
		SecurityContextHolder.setContext(securityContext);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn(mockUser.getEmail());
		when(userService.findByEmail(anyString())).thenReturn(mockUser);
		
		
	}
	
	@Test
	public void dashboard_WhenAdmin_ShouldReturnAdminDashboard() throws Exception {
		
		//Arrange
		mockUser.setRole(Role.ADMIN);
		List<Product> products = new ArrayList<>();
		List<Purchase> purchases = new ArrayList<>();
		List<User> users = new ArrayList<>();
		
		when(productService.getAllProducts()).thenReturn(products);
		when(purchaseService.getAllPurchases()).thenReturn(purchases);
		when(userService.getAllUsers()).thenReturn(users);
		
		//Act & Assert
		mockMvc.perform(get("/dashboard"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("products"))
				.andExpect(model().attributeExists("purchases"))
				.andExpect(model().attributeExists("users"))
				.andExpect(view().name("/admin/admin-dashboard"));
	}
	
	@Test
	public void dashboard_WhenUser_ShouldReturnUserDashboard() throws Exception {
		
		List<Product> products = new ArrayList<>();
		List<Purchase> purchases = new ArrayList<>();
		
		when(productService.getAllProducts()).thenReturn(products);
		when(purchaseService.getAllPurchases()).thenReturn(purchases);
		
		//Act & Assert
				mockMvc.perform(get("/dashboard"))
						.andExpect(status().isOk())
						.andExpect(model().attributeExists("products"))
						.andExpect(model().attributeExists("purchases"))
						.andExpect(view().name("user-dashboard"));

	}
	
	@Test
	public void addToCart_ShouldRedirectToDashboard() throws Exception {
		//Arrange
		Product product = new Product();
		product.setId(2L);
		product.setQuantity(15);
		
		when(productService.getProductById(2L)).thenReturn(product);
		
		//Act and Assert
		mockMvc.perform(post("/dashboard/cart/add")
				.param("productId", "2")
				.param("quantity", "15"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/dashboard"));
		
	}
	
	
	@Test
	public void viewCar_ShouldReturnCartPage() throws Exception {
		
		mockMvc.perform(get("/dashboard/cart"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("cart"))
				.andExpect(view().name("cart"));
	}
	@Test
	public void viewProfile_ShouldReturnProfilePage() throws Exception {
		
		mockMvc.perform(get("/dashboard/profile"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(view().name("profile"));
		
	}
	
	
	@Test
	public void viewOrderHistory_ShouldReturnOrderHistory() throws Exception {
		
		mockMvc.perform(get("/dashboard/orderhistory"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("purchases"))
				.andExpect(view().name("order-history"));
	}
	
	
	
	
	

}
