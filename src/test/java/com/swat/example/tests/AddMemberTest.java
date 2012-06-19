package com.swat.example.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swat.AdminAsserts;
import com.swat.AdminSteps;
import com.swat.data.UserData;

public class AddMemberTest extends BaseTestCase {

	private AdminSteps steps = new AdminSteps(getDriver());
	private AdminAsserts asserts = new AdminAsserts(steps);
	private UserData newMember = new UserData("user", "password");

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		steps.login();
	}
	
	@Test
	public void addNewMember() {
		steps.openAddNewMember();
		steps.addMember(newMember);
		asserts.thatEditUserPageOpened();
	}
	
}
