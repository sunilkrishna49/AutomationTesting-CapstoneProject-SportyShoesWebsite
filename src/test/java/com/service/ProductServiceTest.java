package com.service;

import java.util.List;
import java.util.Arrays;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;

import static org.mockito.Mockito.when;

import com.dao.ProductRepository;
import com.entity.Product;

public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this); // Mockito to create mock instances for any fields annotated with @Mock in the test class.
	}
	
	@Test
	void getAllProducts_ShouldReturnListOfProducts() {
		
	     // Arrange: create sample products and mock repository behavior
		Product product1 = new Product("Reebok Shoes", "Shoes for Jumping", 60.0, 10);
        Product product2 = new Product("Nike Sneakers", "Stylish sneakers", 45.0, 20);
        
        List<Product> products = Arrays.asList(product1, product2);
        
        
        when(productRepository.findAll()).thenReturn(products); //Mocking
        
        //Act: Call the service method
        List<Product> result = productService.getAllProducts();
        
        //Assert: Verify the result
        Assert.assertEquals(2, result.size(), "The product list size should match the expected size");
		Assert.assertEquals("Reebok Shoes", result.get(0).getName(), "The first product name should match");
		Assert.assertEquals("Nike Sneakers", result.get(1).getName(), "The second product name should match");
		
		
	}

}
