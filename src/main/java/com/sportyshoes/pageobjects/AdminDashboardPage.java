package com.sportyshoes.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPage {
	
	WebDriver driver;
	
	//constructor
	
	public AdminDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElements
	@FindBy(xpath="//h1[normalize-space()='Admin Dashboard']") public WebElement adminDashboardHeader;
	
	@FindBy(xpath="//h2[normalize-space()='Product List']/following-sibling::table[1]/tbody/tr") public List<WebElement> productRows;
	
	@FindBy(xpath="//button[normalize-space()='Add']") public WebElement productAddButton;
	
	@FindBy(xpath="//input[@id='name']") public WebElement productNameField;
	
	@FindBy(xpath="//input[@id='description']") public WebElement productDescField;
	
	@FindBy(xpath="//input[@id='price']") public WebElement productPriceField;
	
	@FindBy(xpath="//input[@id='quantity']") public WebElement productQuantityField;
	
	@FindBy(xpath="//input[@placeholder='Search by PName']") public WebElement productSearchInputField;
	
	@FindBy(xpath="//button[normalize-space()='Search']") public WebElement productSearchButton;
	
	@FindBy(xpath="//h3[normalize-space()='Product Search']/following-sibling::table[1]/tbody/tr") public List<WebElement> searchResultRows;
	
	@FindBy(xpath="//h2[normalize-space()='All Users']/following-sibling::table[1]/tbody/tr") public List<WebElement> userRows;
	
	@FindBy(xpath="//button[normalize-space()='Logout']") public WebElement logoutButton;
	
	
	
	//Actions
	public String getDashboardHeaderText() {
		return adminDashboardHeader.getText();
	}
	
	public int getProductCount() {
		return productRows.size();
	}
	public String getProductName(int rowIndex) {
		return productRows.get(rowIndex).findElement(By.xpath("td[1]")).getText();
	}
	public String getProductDescription(int rowIndex) {
		return productRows.get(rowIndex).findElement(By.xpath("td[2]")).getText();
	}
	public String getProductPrice(int rowIndex) {
		return productRows.get(rowIndex).findElement(By.xpath("td[3]")).getText();
	}
	public String getProductQuantity(int rowIndex) {
		return productRows.get(rowIndex).findElement(By.xpath("td[4]")).getText();
	}
	
	public void addNewProduct(String name, String desc, String price, String quantity) {
		productNameField.sendKeys(name);
		productDescField.sendKeys(desc);
		productPriceField.sendKeys(price);
		productQuantityField.sendKeys(quantity);
		productAddButton.click();
	}
	
	public void searchProductByName(String searchProductName) {
		productSearchInputField.sendKeys(searchProductName);
		productSearchButton.click();
	}
	public int getSearchResultCount() {
		return searchResultRows.size();
	}
	
	public int getUserCount() {
		return userRows.size();
	}
	
	
	public void clickLogout() {
		logoutButton.click();
	}
	
	
	
	
	
	
	
	
	
	
	

}
