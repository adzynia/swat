package com.swat;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestProperties {

	private static Properties TEST_PROPERTIES;

	private TestProperties() {
	}

	static {
		TEST_PROPERTIES = new Properties();
		URL testUrl = ClassLoader.getSystemResource("test.properties");

		try {
			TEST_PROPERTIES.load(testUrl.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTestProperty(String key) {
		return TEST_PROPERTIES.getProperty(key);
	}

	public static int getWaitTime() {
		return Integer.parseInt(getTestProperty("wait.time.sec"));
	}

}
