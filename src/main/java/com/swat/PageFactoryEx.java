package com.swat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class PageFactoryEx {

	public static <T> T init(WebDriver driver, Class<T> pageClassToProxy) {
		return initElements(new DefaultElementLocatorFactory(driver), driver, pageClassToProxy);
	}

	public static <T extends BasePage> void initWithFactory(ElementLocatorFactory factory, WebDriver driver, T page) {
		PageFactory.initElements(factory, page);
		initContainers(factory, driver, page);
	}

	private static <T> T initElements(ElementLocatorFactory factory, WebDriver driver, Class<T> pageClassToProxy) {
		T container = instantiatePage(driver, pageClassToProxy);
		PageFactory.initElements(factory, container);
		initContainers(factory, driver, container);
		return container;
	}

	private static <T> void initContainers(ElementLocatorFactory factory, WebDriver driver, T container) {
		Class<?> proxyIn = container.getClass();
		while (proxyIn != Object.class) {
			proxyFields(factory, driver, container, proxyIn);
			proxyIn = proxyIn.getSuperclass();
		}
	}

	private static void proxyFields(ElementLocatorFactory factory, WebDriver driver, Object page, Class<?> proxyIn) {
		Field[] fields = proxyIn.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Container.class) != null) {
				try {
					field.setAccessible(true);
					field.set(page, initElements(factory, driver, field.getType()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
		try {
			try {
				Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClassToProxy.newInstance();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
