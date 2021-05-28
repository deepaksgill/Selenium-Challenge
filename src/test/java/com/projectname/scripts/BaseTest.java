package com.projectname.scripts;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.projectname.framework.Common;
import com.projectname.framework.Config;
import com.projectname.framework.Driver;
import com.projectname.framework.ProLogger;
import com.projectname.pages.Project_PageFactory;

public abstract class BaseTest {

	protected static WebDriver driver;
	protected static Project_PageFactory prePageFactory;
	Common common;

	@BeforeMethod
	public void beforeMethod() {
		ProLogger.startTestCase(this.getClass().getSimpleName());
		
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.isSuccess()) {
			System.out.println("Test Executed Successfully");
			ProLogger.endTestCase(this.getClass().getSimpleName());
		} else if (!result.isSuccess()) {
			System.out.println("Test Not Executed Successfully");
			ProLogger.endTestCase(this.getClass().getSimpleName());
			ProLogger.logMyScreenshot(driver, "", "");

		}

	}


	@BeforeTest
	@Parameters("browserName")
	public void beforeTest(String browserName) {
		driver = new Driver().fGetWebDriver(browserName);//("CHROME");
		driver.manage().deleteAllCookies();
		launchAndLogin(Config.blaze_Url);
		prePageFactory = new Project_PageFactory(driver);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	@BeforeSuite
	public void beforeSuite() {
		ProLogger.info("Project Suite Execution started");
	}

	@AfterSuite
	public void afterSuite() {
	}

	public void launchAndLogin(String url) {

		driver.get(url);
		Reporter.log("System has successfully moved to " + url + " successfully");
		driver.manage().window().maximize();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			Reporter.log("System has failed to navigate to Blaze Demo");
		}
	}

}
