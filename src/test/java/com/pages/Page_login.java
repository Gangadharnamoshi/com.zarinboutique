package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.common.BaseClass;

import utilities.AutomationUtils;

public class Page_login extends BaseClass{
	
	public Page_login(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@id='login-submit']")
	private WebElement button_login;
	
	public void login() {
		driver.findElement(By.id("SiteCode")).sendKeys(prop.getProperty("sitecode"));
		driver.findElement(By.id("UserName")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("Password")).sendKeys(prop.getProperty("password"));
		AutomationUtils.clickElementUsingJs(button_login, driver);
	}
}
