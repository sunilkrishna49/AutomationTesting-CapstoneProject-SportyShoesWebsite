package com.sportyshoesTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.sportyshoes.pageobjects.EditProfilePage;
import com.sportyshoes.pageobjects.UserLoginPage;
import com.sportyshoes.pageobjects.UserDashboardPage;
import com.sportyshoes.pageobjects.UserProfilePage;

public class UserProfilePageTest extends BaseClass {
	
	private UserDashboardPage userDashboardPage;
	private UserProfilePage userProfilePage;
	
	
	@BeforeMethod
	public void setupLoginPagesAndNavigateToProfile() {
		
		login("user","user4@gmail.com","password");
	
		
		userDashboardPage = new UserDashboardPage(driver);
		userProfilePage = new UserProfilePage(driver);
		userDashboardPage.clickProfile();
		
	}
	
	
	
	@Test(priority=1)
	public void verifyUserProfilePageElements() {

		
		//Verify User Details
		Assert.assertEquals(userProfilePage.getProfileHeaderText(),"User Profile","Profile Header does not match");
		Assert.assertEquals(userProfilePage.getNameText(),"user4","User Name does not match");
		Assert.assertEquals(userProfilePage.getSurnameText(),"user4","User Surname does not match");
		Assert.assertEquals(userProfilePage.getEmailText(),"user4@gmail.com","User Email does not match");

		//Verify Presence of Buttons	
		 Assert.assertTrue(userProfilePage.editProfileButton.isDisplayed(), "Edit Profile button is not displayed");
	     Assert.assertTrue(userProfilePage.viewPastPurchasesButton.isDisplayed(), "View Past Purchases button is not displayed");
	     Assert.assertTrue(userProfilePage.viewCartButton.isDisplayed(), "View Cart button is not displayed");
	     Assert.assertTrue(userProfilePage.backToDashboardButton.isDisplayed(), "Back to Dashboard button is not displayed");
	     Assert.assertTrue(userProfilePage.logoutButton.isDisplayed(), "Logout button is not displayed"); 
		
	}
	
	 @Test(priority = 2)
	    public void testEditProfileNavigation() {
	    	
	       userProfilePage.clickEditProfile();
	       EditProfilePage editProfilePage = new EditProfilePage(driver);
	       Assert.assertTrue(editProfilePage.isEditProfileHeaderPresent(), "Edit Profile Header is not displayed!");
	       
	       //Assert that the "Name" field is displayed and editable
	       Assert.assertTrue(editProfilePage.nameField.isDisplayed(), "Name field is not displayed.");
	       Assert.assertTrue(editProfilePage.nameField.isEnabled(), "Name field is not editable.");

	       // Assert that the "Email" field is displayed and editable
	       Assert.assertTrue(editProfilePage.emailField.isDisplayed(), "Email field is not displayed.");
	       Assert.assertTrue(editProfilePage.emailField.isEnabled(), "Email field is not editable.");

	       // Assert that the "Save" button is displayed
	       Assert.assertTrue(editProfilePage.saveButton.isDisplayed(), "Save button is not displayed.");
	 }

	    @Test(priority = 3)
	    public void testViewPastPurchases() {
	    	

	        userProfilePage.clickViewPastPurchases();
	       
	        // Add assertions to verify the past purchases page is displayed
	    }

	    @Test(priority = 4)
	    public void testViewCart() {
	    	

	        userProfilePage.clickViewCart();
	        
	        // Add assertions to verify the cart page is displayed
	    }

	    @Test(priority = 5)
	    public void testLogout() {
	    	

	        userProfilePage.clickLogout();
	        // Add assertions to verify the user is logged out and redirected to the login page
	    }

}
