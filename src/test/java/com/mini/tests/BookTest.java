package com.mini.tests;

import org.testng.annotations.Test;

import com.mini.AbstractPage;
import com.mini.pages.BookHomePage;

public class BookTest extends BaseTestCase {

	@Test
	public void shouldFindText() {
		BookHomePage home = AbstractPage.create(getDriver(), BookHomePage.class);
		home.open();
	}
	
}
