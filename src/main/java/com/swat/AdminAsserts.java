package com.swat;

import static org.testng.AssertJUnit.assertTrue;

public class AdminAsserts {

	private AdminSteps steps;
	
	public AdminAsserts(AdminSteps steps) {
		this.steps = steps;
	}

	public void thatEditUserPageOpened() {
		assertTrue(steps.editMemberPage.isOpened());
	}

	public void thatAdminIsLoggedIn() {
		assertTrue(steps.adminHomePage.isLoggedIn());
	}

	public void thatUserPresentInViewMembers(String memberName) {
		steps.viewMembersPage.open();
		assertTrue(steps.viewMembersPage.isMemberPresent(memberName));
	}

}
