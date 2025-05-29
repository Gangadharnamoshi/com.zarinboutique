package com.testcases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.common.BaseClass;
import com.pages.Page_AllCollections;
import org.testng.annotations.Listeners;
import extentreport.ExtentReporterNG;

@Listeners(ExtentReporterNG.class)
public class Tc_AllCollections {

	BaseClass baseClass = new BaseClass();

	@BeforeTest
	public void urlLaunch() throws IOException {
		baseClass.setup();
	}

	@Test(groups = { "smoke", "p2" }, testName = "verify_testcase_allcollections")
	public void allCollectionsTest() {
		Page_AllCollections allCollections = new Page_AllCollections(baseClass.getdriver());
		allCollections.isZarinLogoDisplayed();
		allCollections.clickOnAllCollections();
		allCollections.clickOnAvailability();
		allCollections.clickOnInStock();
		allCollections.closeAvailabilityBox();
		allCollections.enterMinimumAndMaximumPrice();
		allCollections.sortByLowToHigh();
		allCollections.clickOnProduct();
		allCollections.addToCart();
		allCollections.clickOnViewCart();
		allCollections.clickOnCheckout();
	}

	@AfterTest
	public void browserClose() {
		baseClass.teardown();
	}

}
