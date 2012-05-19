package com.swat.example.tests;

import org.testng.annotations.Test;

import com.swat.AbstractPage;
import com.swat.pages.BookHomePage;

public class BookTest extends BaseTestCase {

	@Test
	public void shouldFindText() {
		BookHomePage home = AbstractPage.create(getDriver(), BookHomePage.class);
		home.open();
	}
	
}
