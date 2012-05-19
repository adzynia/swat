package com.swat.containers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.swat.AbstractContainer;
import com.swat.CommonStrings;

public class Form extends AbstractContainer {

	@FindBy(xpath = "//form")
	private WebElement context;

	public Form(WebDriver driver) {
		super(driver);
	}

	public Form set(String fieldName, String value) {
		driver.findElement(By.name(fieldName)).sendKeys(value);
		return this;
	}

	public Form set(WebElement element, String value) {
		element.sendKeys(value);
		return this;
	}

	public Form clearFieldByName(String fieldName) {
		driver.findElement(By.name(fieldName)).clear();
		return this;
	}

	public Form clearAndSet(String fieldName, String value) {
		clearFieldByName(fieldName);
		set(fieldName, value);
		return this;
	}

	public Form clearAndSet(WebElement element, String value) {
		clearFieldByName(element);
		set(element, value);
		return this;
	}

	public void clearFieldByName(WebElement element) {
		element.clear();
	}

	public String getValueByFieldName(String fieldName) {
		return driver.findElement(By.name(fieldName)).getAttribute(CommonStrings.VALUE);
	}

	public String getValueByField(WebElement element) {
		return element.getAttribute(CommonStrings.VALUE);
	}

	public void submit() {
		context.submit();
	}

}
