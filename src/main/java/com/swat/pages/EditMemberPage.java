package com.swat.pages;

import org.openqa.selenium.WebDriver;

import com.swat.staticdata.PageTitle;
import com.swat.staticdata.PageUrl;

public class EditMemberPage extends AddMemberPage {

	public EditMemberPage(WebDriver driver) {
		super(driver, PageTitle.ADMIN_EDIT_MEMBER, PageUrl.ADMIN_EDIT_MEMBER);
	}

}
