package com.mini.pages;

import org.openqa.selenium.WebDriver;

import com.mini.AbstractPage;
import com.mini.staticdata.PageTitle;
import com.mini.staticdata.PageUrl;

public class BookHomePage extends AbstractPage {

	public BookHomePage(WebDriver driver) {
		super(driver, PageTitle.BOOK_HOME, PageUrl.BOOK_HOME);
	}

}
