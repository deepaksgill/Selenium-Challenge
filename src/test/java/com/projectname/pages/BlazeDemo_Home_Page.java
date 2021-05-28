package com.projectname.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.projectname.framework.Common;
import com.projectname.framework.Config;
import com.projectname.framework.ProLogger;

public class BlazeDemo_Home_Page {

	WebDriver driver;
	Common common;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'home')]")
	private WebElement Home;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'BlazeDemo')]")
	private WebElement headerHomepage;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Register')]")
	private WebElement goToRegistrationPage;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Login')]")
	private WebElement goToLoginPage;
	
	
	
	@FindBy(how = How.CSS, using = "div.panel-heading")
	private WebElement headerLoginPage;
	
	@FindBy(how = How.ID, using = "name")
	private WebElement registerName;

	@FindBy(how = How.ID, using = "company")
	private WebElement registerCompany;

	@FindBy(how = How.ID, using = "email")
	private WebElement registerEmail;

	@FindBy(how = How.ID, using = "password")
	private WebElement registerPassword;

	@FindBy(how = How.ID, using = "password-confirm")
	private WebElement registerConfEmail;

	public boolean navigate_To_BlazeDemo_HomePage() {

		common.clickOnObject(Home, "BlazeDemo Home Page");
		String headerBlazeDemo = common.getElementText(headerHomepage, "BlazeDemo Home Page Header");

		try {
			assertEquals(headerBlazeDemo.trim(), "BlazeDemo");
			ProLogger.info("System has successfully navigated to \"BlazeDemo_HomePage Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"BlazeDemo_HomePage Page\"",
					"System successfully navigated to \"BlazeDemo_HomePage Page\"");
			return true;

		} catch (Exception e) {

			ProLogger.info("System has failed to navigate to \"BlazeDemo_HomePage Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"BlazeDemo_HomePage Page\"",
					"System failed to navigate to \"BlazeDemo_HomePage Page\"");
			return false;
		}

	}

	public boolean goToRegisterPage() {

		common.clickOnObject(goToRegistrationPage, "Goto Registration Page");

		boolean status = common.isElementPresent(registerName, "Register Name ");

		try {
			assertEquals(status, true);
			ProLogger.info("System has successfully navigated to \"BlazeDemo_Registration Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"BlazeDemo_Registration Page\"",
					"System successfully navigated to \"BlazeDemo_Registration Page\"");
			return true;

		} catch (Exception e) {

			ProLogger.info("System has failed to navigate to \"BlazeDemo_Registration Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"BlazeDemo_Registration Page\"",
					"System failed to navigate to \"BlazeDemo_Registration Page\"");
			return false;
		}

	}

	public boolean register_User() {
		try {
			boolean name = common.enterData(registerName, Config.UserDetails[0], "register_Name");
			assertEquals(name, true);
			boolean company = common.enterData(registerCompany, Config.UserDetails[1], "register_Company");
			assertEquals(company, true);
			boolean email = common.enterData(registerEmail, Config.UserDetails[2], "register_Email");
			assertEquals(email, true);
			boolean password = common.enterData(registerPassword, Config.UserDetails[3], "register_Password");
			assertEquals(name, password);
			boolean cpassword = common.enterData(registerConfEmail, Config.UserDetails[4], "register_ConfirmPassword");
			assertEquals(name, cpassword);
			
			ProLogger.info("System has successfully fill the registration form");
			ProLogger.logMyScreenshot(driver, "System should fill the registration form",
					"System successfully fill the registration form");
			return true;
		} catch (Exception e) {
			
			ProLogger.info("System has failed to fill the registration form");
			ProLogger.logMyScreenshot(driver, "System should fill the registration form",
					"System has failed to fill the registration form");
			return true;
		}
			
	}
	
	public boolean navigate_To_BlazeDemo_LoginPage() {
		common.clickOnObject(goToLoginPage, "Goto Login Page");
		String loginBlazeDemo = common.getElementText(headerLoginPage, "BlazeDemo Login Page Header");

		try {
			assertEquals(loginBlazeDemo.trim(), "Login");
			ProLogger.info("System has successfully navigated to \"BlazeDemo_Login Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"BlazeDemo_Login Page\"",
					"System successfully navigated to \"BlazeDemo_Login Page\"");
			return true;

		} catch (Exception e) {

			ProLogger.info("System has failed to navigate to \"BlazeDemo_Login Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"BlazeDemo_Login Page\"",
					"System failed to navigate to \"BlazeDemo_Login Page\"");
			return false;
		}

	}

	public BlazeDemo_Home_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		common = new Common(driver);
	}
}
