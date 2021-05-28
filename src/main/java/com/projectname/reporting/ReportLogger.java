package com.projectname.reporting;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.projectname.framework.ProLogger;

public class ReportLogger {

	File dest;
	String log4jlogs = "Y";
	String flagForScreenShotEnable;

	public ReportLogger() {
	}

	public void generateLogs(WebDriver d, WebElement element, String expectedtext, String actualtext, String teststatus)
			throws IOException {
		getScreenShot(d, element, expectedtext, actualtext, teststatus);
	}

	private void getScreenShot(WebDriver driver, WebElement element, String expectedtext, String actualtext,
			String teststatus) throws IOException {
		flagForScreenShotEnable = "Y";
		StringBuilder href = null;
		try {
			if (flagForScreenShotEnable.equalsIgnoreCase("Y")) {
				File file = new File("");
				Calendar lCDateTime = Calendar.getInstance();
				String a = lCDateTime.getTimeInMillis() + ".jpg";
				System.out.println("TimePeriod of calendar" + a);
				if (driver != null) {
					if (element == null) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].style.border='3px solid red'", element);

					}
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					String f = System.getProperty("user.dir") + "\\target\\screenshots\\" + lCDateTime.getTimeInMillis()
							+ ".png";
					System.out.println(f);
					String f1 = file.getCanonicalPath() + File.separator + "target" + File.separator + "screenshots"
							+ File.separator + lCDateTime.getTimeInMillis() + ".jpg";

					System.out.println("Path of screenshot" + f1);
					dest = new File(f);
					FileUtils.copyFile(scrFile, dest);

					href = new StringBuilder();

					Reporter.log(
							" Expected:::" + expectedtext + "  Actual::: " + actualtext
									+ href.append("<div><a HREF='" + dest.getAbsolutePath() + "'><img src='"
											+ dest.getAbsolutePath()
											+ "' alt='Screen Shot' style='width:250px;height:200px'></a></div>"));
				} else {
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					String f = System.getProperty("user.dir") + "\\target\\screenshots\\" + lCDateTime.getTimeInMillis()
							+ ".png";
					System.out.println(f);
					String f1 = file.getCanonicalPath() + File.separator + "target" + File.separator + "screenshots"
							+ File.separator + lCDateTime.getTimeInMillis() + ".jpg";

					System.out.println("Path of screenshot" + f1);
					dest = new File(f);
					FileUtils.copyFile(scrFile, dest);

					href = new StringBuilder();

					Reporter.log(
							" Expected:::" + expectedtext + "  Actual::: " + actualtext
									+ href.append("<div><a HREF='" + dest.getAbsolutePath() + "'><img src='"
											+ dest.getAbsolutePath()
											+ "' alt='Screen Shot' style='width:250px;height:200px'></a></div>"));
				}
			}
		} catch (Exception e) {

			Reporter.log(e.toString());
			Reporter.log(" Expected:::" + expectedtext + "  Actual::: " + actualtext);
		}
	}

	public void myScreenshot(WebDriver d, WebElement element, String expectedtext, String actualtext,
			String teststatus) {
		Calendar lCDateTime = Calendar.getInstance();
		StringBuilder href = new StringBuilder();
		String a = lCDateTime.getTimeInMillis() + ".jpg";

		try {
			TakesScreenshot scrShot = ((TakesScreenshot) d);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "\\target\\screenshots\\" + a;
			System.out.println("Screenshot saved at :" + path);
			File dest = new File(path);
			// Utils.copyFile(SrcFile, dest);
			FileUtils.copyFile(SrcFile, dest);
			ProLogger.info("Screenshot taken successfully");
			Reporter.log(" Expected:::" + expectedtext + "  Actual::: " + actualtext
					+ href.append("<div><a HREF='" + dest.getAbsolutePath() + "'><img src='" + dest.getAbsolutePath()
							+ "' alt='Screen Shot' style='width:250px;height:200px'></a></div>"));

		} catch (Exception e) {
			ProLogger.info("Screenshot is unable to generate because of " + e.toString());
		}

		// Move image file to new destination

	}

}
