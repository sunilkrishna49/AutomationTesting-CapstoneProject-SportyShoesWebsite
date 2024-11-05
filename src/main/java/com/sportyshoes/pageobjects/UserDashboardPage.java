package com.sportyshoes.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserDashboardPage {
	
	WebDriver driver;
	
	
	//Constructor
	
	public UserDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElements
	@FindBy(xpath = "//h1[@id='dashboard']") WebElement dashboardHeader;
	
	@FindBy(xpath ="//table/tbody/tr/td[1]") List<WebElement> productNames;
	
	@FindBy(xpath="//table/tbody/tr/td[2]") List<WebElement> productDescriptions;
	
	@FindBy(xpath="//table/tbody/tr/td[3]") List<WebElement> productPrices;
	
	@FindBy(xpath="(//button[@type='submit'][normalize-space()='Add To Cart'])") List<WebElement> addToCartButtons;
	
	@FindBy(css="a[href='/dashboard/cart']") WebElement viewCartLink;
	
	@FindBy(xpath="//a[normalize-space()='Profile']") WebElement profileButton;
	
	@FindBy(xpath="//a[normalize-space()='Order History']") WebElement orderHistoryButton;
	
	@FindBy(xpath="//a[normalize-space()='Logout']") WebElement logoutButton;
	

	
	@FindBy(xpath="//table/tbody/tr/td[1]") List<WebElement> cartProductNames;
	
	@FindBy(css="a[href='/dashboard/cart/remove/0']") WebElement removeButton;
	
	//Getter Methods
	
	public List<WebElement> getProductNames(){
		return productNames;
	}
	
	public List<WebElement> getProductDescriptions(){
		return productDescriptions;
	}
	public List<WebElement> getProductPrices(){
		return productPrices;
	}

	
	
	//Actions
	
	public String getDashboardHeaderText() {
		return dashboardHeader.getText();
	}
	public int getProductCount() {
		return productNames.size();
	}
	
	public String getProductName(int productIndex) {
		return productNames.get(productIndex).getText();
		
	}
	public void addProductToCart(int productIndex) {
		addToCartButtons.get(productIndex).click();
		
		//Handle the alert after clicking Add to Cart
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("Alert text: "+ alert.getText()); //For Debugging
			alert.accept(); // Accept the alert;
			
		}catch(Exception e) {
			
			System.out.println("No alert present after clicking add to cart");
			
		}
		
	}
	public void clickViewCart()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(viewCartLink)).click();
	}
	public void clickProfile() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(profileButton)).click();
	}
	public void clickOrderHistory() {
		orderHistoryButton.click();
	}
	public void clickLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(logoutButton)).click();
	
	}
	public void clickRemove() {
		removeButton.click();
	}
	
	public boolean isProductInCart(String productName) {
		for (WebElement product: cartProductNames) {
		
			if (product.getText().equalsIgnoreCase(productName)) {
				return true;
			}
				
			}
		return false;
	}
	

	
	
	
	

}
