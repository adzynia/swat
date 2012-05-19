package com.swat;

import org.openqa.selenium.WebDriver;

public class AbstractContainer {
	
	protected WebDriver driver;

	public AbstractContainer(WebDriver driver) {
		this.driver = driver;
	}

}
