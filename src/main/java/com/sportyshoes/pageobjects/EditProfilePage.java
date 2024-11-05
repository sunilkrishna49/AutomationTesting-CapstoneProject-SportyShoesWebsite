package com.sportyshoes.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProfilePage {
	
	WebDriver driver;
	
	//Constructors
	public EditProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElements
	@FindBy(xpath="//h1[normalize-space()='Edit Profile']") WebElement editProfileHeader;
	
	@FindBy(id = "name")
	  public WebElement nameField;

	 @FindBy(id = "email")
	  public WebElement emailField;

	 @FindBy(xpath = "//button[normalize-space()='Save Changes']")
	   public WebElement saveButton;
	
	
	//Actions
	
	public boolean isEditProfileHeaderPresent() {
		return editProfileHeader.isDisplayed();
	}
	

}
