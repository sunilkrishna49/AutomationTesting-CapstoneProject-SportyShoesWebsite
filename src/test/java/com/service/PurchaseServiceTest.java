package com.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import com.dao.PurchaseRepository;
import com.entity.Purchase;
import com.entity.User;

public class PurchaseServiceTest {
	
	@Mock
	private PurchaseRepository purchaseRepository;
	
	@InjectMocks
	private PurchaseService purchaseService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void getPurchasesByUser_ShouldReturnListOfPurchases_ForGivenUser() {
		
		User user = new User();
		user.setId(1L);
		
		Purchase purchase1 = new Purchase();
		purchase1.setUser(user);
		Purchase purchase2 = new Purchase();
		purchase2.setUser(user);
		
		when(purchaseRepository.findByUser(user)).thenReturn(Arrays.asList(purchase1, purchase2));
		
		//Act
		List<Purchase> purchases = purchaseService.getPurchasesByUser(user);
		
		//Assertions
		assertEquals(2, purchases.size());
		verify(purchaseRepository, times(1)).findByUser(user);	
	}
	
	
	

}
