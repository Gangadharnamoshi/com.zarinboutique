package com.testcases;

import java.io.IOException;

import org.testng.annotations.*;

import com.common.BaseClass;
import com.pages.Page_AllCollections;

import extentreport.ExtentReporterNG;
import org.testng.annotations.Listeners;

@Listeners(ExtentReporterNG.class)
public class Tc_AllCollections extends BaseClass {

    @Parameters("browser")
    @BeforeMethod
    public void urlLaunch(String browser) throws IOException {
        setup(browser); // use inherited method from BaseClass
    }

    @Test(groups = { "smoke", "p2", "regression" }, priority = 1, testName = "verify_testcase_allcollections")
    public void allCollectionsTest() {
        Page_AllCollections allCollections = new Page_AllCollections(); // no need to pass driver
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

    @AfterMethod
    public void browserClose() {
        teardown(); // inherited, thread-safe
    }
}
