package com.projectname.framework;

import java.io.File;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.internal.Utils;

public class ProLogger {

//Initialize Log4j logs

	private static Logger Log = Logger.getLogger(ProLogger.class.getName());//
	FileAppender fileAppender = (FileAppender) Log.getAppender(System.getProperty("user.dir") + "\\src\\main\\resources\\log4j.xml");

// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

	public static void startTestCase(String sTestCaseName) {

		ProLogger.info("****************************************************************************************");
		ProLogger.info("****************************************************************************************");
		ProLogger.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		ProLogger.info("****************************************************************************************");
		ProLogger.info("****************************************************************************************");

	}

	// This is to print log for the ending of the test case

	public static void endTestCase(String sTestCaseName) {

		ProLogger.info("****************************************************************************************");
		ProLogger.info("****************************************************************************************");
		ProLogger.info("XXXXXXXXXXXXXXXXXXXXXXX             \"+\"-E---N---D-\"+\"             XXXXXXXXXXXXXXXXXXXXXX");
		ProLogger.info("****************************************************************************************");
		ProLogger.info("****************************************************************************************");

	}

	// Need to create these methods, so that they can be called

	public static void info(String message) {
		DOMConfigurator.configure(System.getProperty("user.dir") + "\\src\\main\\resources\\log4j.xml");
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}

	public static void logMyScreenshot(WebDriver d, String expectedtext, String actualtext) {
		Calendar lCDateTime = Calendar.getInstance();
		StringBuilder href = new StringBuilder();
		String a = lCDateTime.getTimeInMillis() + ".jpg";

		try {
			TakesScreenshot scrShot = ((TakesScreenshot) d);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "\\target\\screenshots\\" + a;
			File dest = new File(path);
			FileUtils.copyFile(SrcFile, dest);
			ProLogger.info("Screenshot taken successfully");
			Reporter.log(" Expected:::" + expectedtext + "  Actual::: " + actualtext
					+ href.append("<div><a HREF='" + dest.getAbsolutePath() + "'><img src='" + dest.getAbsolutePath()
							+ "' alt='Screen Shot' style='width:250px;height:200px'></a></div>"));

		} catch (Exception e) {
			ProLogger.info("Screenshot is unable to generate because of " + e.toString());
		}

	}

}