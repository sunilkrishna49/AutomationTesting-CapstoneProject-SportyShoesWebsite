package com.sportyshoes.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserProfilePage {
	
	WebDriver driver;
	
	//Constructors
	public UserProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElements
	@FindBy(xpath="//h1[normalize-space()='User Profile']") WebElement profileHeader;
	
	//Explanation:
	//div[@class='profile-info']: Locates the div with the class profile-info.
	///p[1]/span: Within this div, it selects the first p element and then its span child, which contains the user's name.
	@FindBy(xpath="//div[@class='profile-info']/p[1]/span") WebElement nameElement;
	
	
	
	@FindBy(xpath="//p[2]//span[1]") WebElement surnameElement;
	@FindBy(xpath="//p[3]//span[1]") WebElement emailElement;
	@FindBy(xpath = "//a[normalize-space()='Edit Profile']") public WebElement editProfileButton;
    @FindBy(xpath = "//a[normalize-space()='View Past Purchases']") public WebElement viewPastPurchasesButton;
    @FindBy(xpath = "//a[normalize-space()='View Cart']") public WebElement viewCartButton;
    @FindBy(xpath = "//a[normalize-space()='Back to Dashboard']") public WebElement backToDashboardButton;
    @FindBy(xpath = "//a[normalize-space()='Logout']") public WebElement logoutButton;
	
	
	
	//Actions
	public String getProfileHeaderText() {
		return profileHeader.getText();
	}
	public String getNameText() {
		return nameElement.getText();
	}
	public String getSurnameText() {
		return surnameElement.getText();
	}
	public String getEmailText() {
		return emailElement.getText();
	}
	 public void clickEditProfile() {
		 editProfileButton.click();
	    }

	 public void clickViewPastPurchases() {
		 viewPastPurchasesButton.click();
	    }

	 public void clickViewCart() {
	        viewCartButton.click();
	    }

	 public void clickBackToDashboard() {
	        backToDashboardButton.click();
	    }

	 public void clickLogout() {
	        logoutButton.click();
	    }

}
