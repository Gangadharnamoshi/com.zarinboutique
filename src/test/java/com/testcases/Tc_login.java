package com.testcases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.common.BaseClass;
import com.pages.Page_login;

import extentreport.ExtentReporterNG;
@Listeners(ExtentReporterNG.class)
public class Tc_login  {
	
	BaseClass base = new BaseClass();
	
	@BeforeTest
	public void urlLanuch() throws IOException {
		base.setup();
	}
	
	@Test
	public void login() {
		Page_login login = new Page_login(base.getdriver());
		login.login();
	}
	
	@AfterTest
	public void urlClose() {
		base.teardown();
	}

}
