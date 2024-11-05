package com.sportyshoesTestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.sportyshoes.pageobjects.AdminDashboardPage;

public class AdminDashboardPageTest extends BaseClass {
	
	
	@BeforeMethod
	public void adminLogin() {
		login("admin","admin@admin.com","a");
		
	}
	
	@Test(priority=1)
	public void verifyDashboardHeader() {
		
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		
		Assert.assertEquals(adminDashboardPage.getDashboardHeaderText(),"Admin Dashboard","Dashboard Header does not match");
		
	}
	
	@Test(priority=2)
	public void verifyProductList() {
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		
		//Ensure there is atleast one product;
		Assert.assertTrue(adminDashboardPage.getProductCount()>0);
		
		//Validate details of the first product (change index to validate more products if needed)
		String firstProductName = adminDashboardPage.getProductName(0);
		String firstProductDescription = adminDashboardPage.getProductDescription(0);
		String firstProductPrice = adminDashboardPage.getProductPrice(0);
		String firstProductQuantity = adminDashboardPage.getProductQuantity(0);
		
		System.out.println("First Product Name: " + firstProductName);
        System.out.println("First Product Description: " + firstProductDescription);
        System.out.println("First Product Price: " + firstProductPrice);
        System.out.println("First Product Quantity: " + firstProductQuantity);
		
     // Assert specific product details if you have expected values
        Assert.assertFalse(firstProductName.isEmpty(), "Product name should not be empty.");
        Assert.assertFalse(firstProductDescription.isEmpty(), "Product description should not be empty.");
        Assert.assertFalse(firstProductPrice.isEmpty(), "Product price should not be empty.");
        Assert.assertFalse(firstProductQuantity.isEmpty(), "Product quantity should not be empty.");
  	
	}
	
	@Test(priority=3)
	public void addProductTest() {
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		
		adminDashboardPage.addNewProduct("Steel Toe Boots Nike", "Durable boots with reinforced toe for industrial use", "85", "24");
		
		Assert.assertTrue(adminDashboardPage.getProductCount()>0, "Failed to add product.");
	}
	
	@Test(priority=4)
	public void productSearchTest() {
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		
		String searchProductName = "Casual Sneakers";
		adminDashboardPage.searchProductByName(searchProductName);
		
		Assert.assertTrue(adminDashboardPage.getSearchResultCount()>0, "No Results found for product search");
		
		String firstResultName = adminDashboardPage.searchResultRows.get(0).getText();
		Assert.assertTrue(firstResultName.contains(searchProductName), "Search Result doesnot match the search term");
	}
	
	public void verifyUserListNotEmpty() {
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		
		Assert.assertTrue(adminDashboardPage.getUserCount()>0, "Users List is empty on Admin Dashboard Page.");
		
	}
	
	@Test(priority=5)
	public void verifyLogout() {
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		
		adminDashboardPage.clickLogout();
		
		String CurrentUrl = driver.getCurrentUrl();
		Assert.assertTrue(CurrentUrl.contains("/login"), "Logout Failed, Still on Dashboard Page");
		
	}
	
	

}
