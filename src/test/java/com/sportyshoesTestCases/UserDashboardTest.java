package com.sportyshoesTestCases;

import com.base.BaseClass;
import com.sportyshoes.pageobjects.UserLoginPage;
import com.sportyshoes.pageobjects.UserDashboardPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class UserDashboardTest extends BaseClass {
	
	
	private UserDashboardPage userDashboardPage;
	
    @Test(priority=1)
    public void verifyUserDashboardElements() {
    	
    	driver.get("http://localhost:9292/login");
    	
    	UserLoginPage loginPage = new UserLoginPage(driver);
    	userDashboardPage = new UserDashboardPage(driver);
		loginPage.enterEmail("user4@gmail.com");
		loginPage.enterPassword("password");
		loginPage.clickLogin();
    			
    	//Verify Dashboard header
		Assert.assertEquals(userDashboardPage.getDashboardHeaderText(), "User Dashboard","Dashboard header text does not match");
		
		//Verify Product count is greater than zero;
		Assert.assertTrue(userDashboardPage.getProductCount()>0,"Product List is empty on the User Dashboard.");	
    }
   
    
    @Test(priority = 2)
    public void addToCartTest() {
        UserDashboardPage userDashboardPage = new UserDashboardPage(driver);

        int productCount = userDashboardPage.getProductCount();
        Assert.assertTrue(productCount > 0, "No products found to add to cart.");

        // Add the first product to the cart if it exists
        if (productCount > 0) {
            userDashboardPage.addProductToCart(0);
            userDashboardPage.clickViewCart();

            // Additional assertions can be added to confirm item was added to the cart
        } else {
            System.out.println("No products available to add to cart.");
        }
        userDashboardPage.clickRemove();
    }

    
   @Test(priority=3)
    public void verifyProductDetails() throws InterruptedException {
    	
    	for(int i=0;i<userDashboardPage.getProductCount();i++) {
    		
    		WebElement productName = userDashboardPage.getProductNames().get(i);
    		WebElement productDescription= userDashboardPage.getProductDescriptions().get(i);
    		WebElement productPrice= userDashboardPage.getProductPrices().get(i);
    		
    		
    		Assert.assertFalse(productName.getText().isEmpty(), "Product Name is empty for item at index "+ i);
    		Assert.assertFalse(productDescription.getText().isEmpty(), "Product Description is empty for item at index "+ i);
    		Assert.assertFalse(productPrice.getText().isEmpty(), "Product Price is empty for item at index "+ i);
    		
    	}
    	
    }
   
   @Test(priority=4)
   public void verifyNavigationLinks() {
	   
	   userDashboardPage.clickViewCart();
	   Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/cart"), "Failed to navigate to cart page");
	   driver.navigate().back();
	   
	   userDashboardPage.clickProfile();
	   Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/profile"), "Failed to navigate to profile page");
	   driver.navigate().back();
	   
	   userDashboardPage.clickOrderHistory();
	   Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/orderhistory"), "Failed to navigate to order history page");
	   driver.navigate().back();
	   
   }
   
   @Test(priority=5)
   public void addMultipleItemsToCart() {
	   
	   userDashboardPage.addProductToCart(0);
	   userDashboardPage.addProductToCart(1);
	   
	   userDashboardPage.clickViewCart();
	   
	   //Check the two items are present in the cart
	   List <WebElement> cartItems = driver.findElements(By.xpath("//table/tbody/tr"));
	   Assert.assertEquals(cartItems.size(), 2, "Cart does not contain the expected number of items.");
	   
	   List <WebElement> cartProductNames = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
	   Assert.assertTrue(cartProductNames.get(0).getText().contains(userDashboardPage.getProductNames().get(0).getText()));
	   Assert.assertTrue(cartProductNames.get(1).getText().contains(userDashboardPage.getProductNames().get(1).getText()));
	   userDashboardPage.clickRemove();
	   userDashboardPage.clickViewCart();
	   userDashboardPage.clickRemove();
   }
   
   
   @Test(priority=6)
   public void verifyLogout() {
	   //Check if logout button is working
	   
	  userDashboardPage.clickLogout();
	  
	  Assert.assertTrue(driver.getCurrentUrl().contains("/login?logout"), "Failed to logout and navigate to login page.");
   }
    
    
}
