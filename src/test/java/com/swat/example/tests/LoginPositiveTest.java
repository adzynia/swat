package com.swat.example.tests;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.swat.BasePage;
import com.swat.data.UserData;
import com.swat.pages.AdminHomePage;
import com.swat.pages.AdminLoginPage;

public class LoginPositiveTest extends BaseTestCase {

	private UserData admin = new UserData("admin", "admin");
	
	@Test
	public void loginAsAdminPositive() {
		AdminLoginPage adminLoginPage = BasePage.create(getDriver(), AdminLoginPage.class);
		adminLoginPage.open();
		AdminHomePage adminHomePage = adminLoginPage.loginAs(admin);
		assertTrue(adminHomePage.isLoggedIn());
	}
	
}
