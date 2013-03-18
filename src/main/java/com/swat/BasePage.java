package com.swat;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.swat.containers.Buttons;
import com.swat.containers.Form;
import com.swat.containers.FormFields;
import com.swat.containers.Table;
import com.swat.staticdata.PageTitle;
import com.swat.staticdata.PageUrl;

public abstract class BasePage extends AbstractContainer {

	private final PageTitle title;
	private final PageUrl pageUrl;

	private final Wait<WebDriver> wait = new WebDriverWait(driver, TestProperties.getWaitTime()).pollingEvery(1, TimeUnit.SECONDS);

	@Container
	private Buttons buttons;

	@Container
	private Table table;

	@Container
	private FormFields formFields;

	@Container
	private Form form;

	public BasePage(WebDriver driver, PageTitle title, PageUrl pageUrl) {
		super(driver);
		this.title = title;
		this.pageUrl = pageUrl;
	}

	public static class CommonPageLocators {
	}

	public Buttons getButtons() {
		return buttons;
	}

	public FormFields getFormFields() {
		return formFields;
	}

	public Form getForm() {
		return form;
	}

	public boolean isOpened() {
		return getTitle().equals(driver.getTitle());
	}

	public void open() {
		driver.get(getPageFullUrl());
		assertEquals(driver.getTitle(), getTitle());
	}

	public String getTitle() {
		return title != null ? title.getTitleValue() : null;
	}

	public static <T extends BasePage> T create(WebDriver driver, Class<T> pageClass) {
		return PageFactoryEx.init(driver, pageClass);
	}

	public static <T extends BasePage> T init(T page) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(page.driver, Integer.parseInt(TestProperties.getTestProperty("wait.time.sec")));
		PageFactoryEx.initWithFactory(finder, page.driver, page);
		return page;
	}

	public String getPageFullUrl() {
		return pageUrl.getUrl();
	}

	public void assertPageTitle() {
		String seleniumTitle = driver.getTitle();
		assertEquals(seleniumTitle, title.getTitleValue(), "Should be on page " + title + " but I am actually on page " + seleniumTitle);
	}

	public boolean isElementVisible(WebElement locator) {
		try {
			locator.isDisplayed();
			return true;
		} catch (ElementNotVisibleException ignored) {
			return false;
		} catch (NoSuchElementException ignored) {
			return false;
		} catch (StaleElementReferenceException ignored) {
			return false;
		}
	}

	public boolean isElementVisible(By by) {
		try {
			driver.findElement(by).isDisplayed();
			return true;
		} catch (NoSuchElementException ignored) {
			return false;
		} catch (ElementNotVisibleException ignored) {
			return false;
		} catch (StaleElementReferenceException ignored) {
			return false;
		}
	}

	public boolean isElementPresent(WebElement locator) {
		try {
			locator.getTagName();
		} catch (NoSuchElementException ignored) {
			return false;
		} catch (StaleElementReferenceException ignored) {
			return false;
		}
		return true;
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException ignored) {
			return false;
		} catch (StaleElementReferenceException ignored) {
			return false;
		}
	}

	public boolean isElementTextPresent(By by, String text) {
		try {
			if (driver.findElement(by).getText().equals(text)) {
				return true;
			}
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementTextPresent(WebElement element, String text) {
		try {
			if (element.getText().equals(text)) {
				return true;
			}
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getName() {
		return this.getClass().getSimpleName() + " [" + getTitle() + "]";
	}

	public boolean isTextPresent(String text) {
		return driver.findElement(By.tagName("body")).getText().contains(text);
	}

	public void close() {
		driver.quit();
	}

	public void acceptConfirmation() {
		driver.switchTo().alert().accept();
	}

	public boolean isTableHeadersPresent(List<String> headerText) {
		List<WebElement> headers = driver.findElements(By.xpath("//th"));
		return isHeaderTextEquals(headerText, headers);
	}

	protected boolean isHeaderTextEquals(List<String> headerText, List<WebElement> headers) {
		if (headers.size() != headerText.size()) {
			return false;
		}

		for (int i = 0; i < headers.size(); i++) {
			WebElement webElement = headers.get(i);
			String text = headerText.get(i);
			if (!isElementTextPresent(webElement, text)) {
				return false;
			}
		}
		return true;
	}

	protected Function<WebDriver, Boolean> visibilityOfElement(final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return element.isDisplayed();
			}
		};
	}

	protected Function<WebDriver, Boolean> hiddentOfElement(final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return !element.isDisplayed();
			}
		};
	}

	protected void sleep() {
		try {
			Thread.sleep(Integer.parseInt(TestProperties.getTestProperty("wait.hack.msec")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void clickAt(WebElement webElement, int x, int y) {
		Actions builder = new Actions(driver);
		Action action = builder.moveToElement(webElement, x, y).click().build();
		action.perform();
	}


	public List<String> getTableHeadersText() {
		List<WebElement> headers = getTable().getHeaders();
		List<String> headerText = new ArrayList<String>();
		for (WebElement element : headers) {
			headerText.add(element.getText());
		}
		return headerText;

	}

	public Table getTable() {
		return table;
	}

	public Wait<WebDriver> getWait() {
		return wait;
	}

}
