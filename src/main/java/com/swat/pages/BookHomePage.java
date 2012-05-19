package com.swat.pages;

import org.openqa.selenium.WebDriver;

import com.swat.AbstractPage;
import com.swat.staticdata.PageTitle;
import com.swat.staticdata.PageUrl;

public class BookHomePage extends AbstractPage {

	public BookHomePage(WebDriver driver) {
		super(driver, PageTitle.BOOK_HOME, PageUrl.BOOK_HOME);
	}

}
