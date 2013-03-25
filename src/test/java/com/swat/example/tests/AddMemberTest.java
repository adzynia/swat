package com.swat.example.tests;

import java.io.IOException;
import java.util.Random;

import com.swat.HttpActions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swat.AdminAsserts;
import com.swat.AdminSteps;
import com.swat.data.UserData;

public class AddMemberTest extends BaseTestCase {

	private AdminSteps steps = new AdminSteps(getDriver());
	private AdminAsserts asserts = new AdminAsserts(steps);
	private UserData newMember = new UserData("user" + new Random().nextInt(), "password");

  @BeforeMethod(alwaysRun = true)
	public void setup() {
		steps.login();
	}
	
	@Test
	public void addNewMember() {
		steps.openAddNewMember();
		steps.addMember(newMember);
		asserts.thatEditUserPageOpened();
		asserts.thatUserPresentInViewMembers(newMember.getName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
    HttpActions.removeMember(newMember.getName());
	}
	
}
