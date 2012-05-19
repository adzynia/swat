package com.swat.containers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.swat.AbstractContainer;

public class FormFields extends AbstractContainer {

	@FindBy(name = FormLocators.INPUT_NAME)
	private WebElement inputName;

	static class FormLocators {
		private static final String INPUT_NAME = "name";
	}

	public FormFields(WebDriver driver) {
		super(driver);
	}

	public WebElement getInputName() {
		return inputName;
	}

}
