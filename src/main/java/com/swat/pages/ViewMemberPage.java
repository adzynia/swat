package com.swat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.swat.BasePage;
import com.swat.staticdata.PageTitle;
import com.swat.staticdata.PageUrl;

public class ViewMemberPage extends BasePage {

	private static final String LINK_DELETE_MEMBER = "/..//img[@title='Delete member']";
	private static final String CELL_MEMBER_NAME = "//td[@class='tdCell' and text()='%s']";

	public ViewMemberPage(WebDriver driver) {
		super(driver, PageTitle.ADMIN_VIEW_MEMBER_PAGE, PageUrl.ADMIN_VIEW_MEMBER_PAGE);
	}

	public boolean isMemberPresent(String memberName) {
    searchMember(memberName);
		return isElementPresent(By.xpath(String.format(CELL_MEMBER_NAME, memberName)));
	}

  public void searchMember(String memberName) {
    driver.findElement(By.name("searchMembers")).sendKeys(memberName);
    driver.findElement(By.xpath("//*[@value='Find']")).click();
  }

  public void clickDeleteMemberBy(String name) {
		driver.findElement(
				By.xpath(String.format(CELL_MEMBER_NAME + LINK_DELETE_MEMBER, name))).click();
	}

}
