package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.common.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

import extentreport.ReportUtil;
import utilities.AutomationUtils;

public class Page_AllCollections extends BaseClass {

	public Page_AllCollections(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='header__heading-link link link--text focus-inset']")
	private WebElement zarinLogo;

	@FindBy(xpath = "//a[@id='HeaderMenu-all-collection']")
	private WebElement allCollections;

	@FindBy(xpath = "(//span[contains(text(),'Availability')])[1]")
	private WebElement availability;

	@FindBy(xpath = "(//span[contains(text(),'In stock')])[1]")
	private WebElement inStock;

	@FindBy(xpath = "(//span[contains(text(),'Price')])[1]")
	private WebElement price;

	@FindBy(xpath = "//input[@id='Filter-Price-GTE']")
	private WebElement input_priceFrom;

	@FindBy(xpath = "//input[@id='Filter-Price-LTE']")
	private WebElement input_priceTo;

	@FindBy(xpath = "//select[@id='SortBy']")
	private WebElement dropDown_sortBy;

	@FindBy(xpath = "//a[@id=\"CardLink-template--23093092057361__product-grid-10214381715729\"]")
	private WebElement product;

	@FindBy(xpath = "//button[@name='add']")
	private WebElement button_addCart;

	@FindBy(xpath = "//a[@id='cart-notification-button']")
	private WebElement viewCart;

	@FindBy(xpath = "//button[@id='checkout']")
	private WebElement checkout;

	public boolean isZarinLogoDisplayed() {
		ReportUtil.logStep("Checking Logo ",LogStatus.INFO);
		highLightElement(zarinLogo);
		ReportUtil.logStep("Logo Dispalyed",LogStatus.PASS);
		return zarinLogo.isDisplayed();
		
	}

	public void clickOnAllCollections() {
		highLightElement(allCollections);
		allCollections.click();
	}

	public void clickOnAvailability() {
		highLightElement(availability);
		AutomationUtils.waitForTheElementAndClick(availability, driver);
	}

	public void clickOnInStock() {
		highLightElement(inStock);
		AutomationUtils.clickElementUsingJs(inStock, driver);
	}
	
	public void closeAvailabilityBox() {
		highLightElement(availability);
		AutomationUtils.clickElementUsingJs(allCollections, driver);
		AutomationUtils.waitForElementToDisapper(allCollections, driver);
	}
	
	

	public void enterMinimumAndMaximumPrice() {
		AutomationUtils.waitForTheElementToBeClickable(price, driver);
		AutomationUtils.clickElementUsingJs(price, driver);
		input_priceFrom.sendKeys("1000");
		input_priceTo.sendKeys("2000");
		// input_priceTo.sendKeys(Keys.ENTER);
	}
	public void closePriceBox() {
		AutomationUtils.clickElementUsingJs(price, driver);
		AutomationUtils.waitForElementToDisapper(price, driver);
	}

	public void sortByLowToHigh() {
		AutomationUtils.selectByVisibleText(dropDown_sortBy, "Price, low to high");
	}

	public void clickOnProduct() {
		AutomationUtils.clickElementUsingJs(product, driver);
	}

	public void addToCart() {
		AutomationUtils.waitForTheElementAndClick(button_addCart, driver);
	}

	public void clickOnViewCart() {
		AutomationUtils.waitForTheElementAndClick(viewCart, driver);
	}

	public void clickOnCheckout() {
		checkout.click();
	}
	
	public void update() {
		
	}

}
