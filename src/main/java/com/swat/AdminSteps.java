package com.swat;

import org.openqa.selenium.WebDriver;

import com.swat.data.UserData;
import com.swat.pages.AddMemberPage;
import com.swat.pages.AdminHomePage;
import com.swat.pages.AdminLoginPage;
import com.swat.pages.EditMemberPage;

public class AdminSteps extends BaseStep {

	AddMemberPage addMemberPage = BasePage.create(getDriver(),
			AddMemberPage.class);
	EditMemberPage editMemberPage = BasePage.create(getDriver(),
			EditMemberPage.class);
	AdminHomePage adminHomePage = BasePage.create(getDriver(),
			AdminHomePage.class);
	private UserData admin = new UserData("admin", "admin");

	public AdminSteps(WebDriver driver) {
		super(driver);
	}

	public void openAddNewMember() {
		addMemberPage.open();
	}

	public void addMember(UserData newMember) {
		addMemberPage.fillmemberForAs(newMember).getForm().submit();
	}

	public void login() {
		AdminLoginPage adminLoginPage = BasePage.create(getDriver(), AdminLoginPage.class);
		adminLoginPage.open();
		adminLoginPage.loginAs(admin);
	}

}
