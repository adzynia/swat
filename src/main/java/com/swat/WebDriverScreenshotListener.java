package com.swat;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class WebDriverScreenshotListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		File outputDirectory = new File(System.getProperty("user.dir"), "target/surefire-reports");
		try {
			outputDirectory.mkdirs();
			File outFile = new File(outputDirectory, "TEST-" + result.getName() + ".png");
			captureScreenshot(outFile);
			Reporter.log("<a href='" + outFile.getName() + "'>screenshot</a>");
		} catch (Exception e) {
			Reporter.log("Couldn't create screenshot");
			Reporter.log(e.getMessage());
		}
		Reporter.setCurrentTestResult(null);
	}

	private static void captureScreenshot(File outFile) throws Exception {
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", outFile);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

}
