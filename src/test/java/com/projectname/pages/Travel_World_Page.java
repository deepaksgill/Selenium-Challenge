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

public class Travel_World_Page {

	WebDriver driver;
	Common common;

	@FindBy(how = How.NAME, using = "fromPort")
	private WebElement select_FromPort;

	@FindBy(how = How.NAME, using = "toPort")
	private WebElement select_ToPort;

	@FindBy(how = How.CSS, using = "form > div > input")
	private WebElement btn_FindFlights;

	@FindBy(how = How.CSS, using = "div.jumbotron > div > h1")
	private WebElement headerMessage;

	@FindBy(how = How.CSS, using = "div.container > form > div > input")
	private WebElement findFlights;
	
	@FindBy(how = How.CSS, using = "div.container > h3")
	private WebElement flightHeaderMessage;

	public boolean goTo_TravelTheWorld_Page() {

		String blaze_Header = common.getElementText(headerMessage, "Blaze DemoHeader");
		try {
			assertEquals(blaze_Header, Config.blaze_Header);
			ProLogger.info("System has successfully navigated to \"Travel The World Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"Travel The World Page\"",
					"System successfully navigated to \"Travel The World Page\"");
			return true;

		} catch (Exception e) {

			ProLogger.info("System has failed to navigate to \"Travel The World Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"Travel The World Page\"",
					"System failed to navigate to \"Travel The World Page\"");
			return true;
		}

	}

	public boolean select_DepartureCity(String departure) {

		boolean status = common.selectByValue(select_FromPort, "FromPortDropdown", departure);

		if (status) {
			ProLogger.info("System has successfully selected departure as : " + departure);
			ProLogger.logMyScreenshot(driver, "System should select departure as : " + departure,
					"System successfully selected departure as : " + departure);
			return true;

		} else {
			ProLogger.info("System has failed to select departure as : " + departure);
			ProLogger.logMyScreenshot(driver, "System should select departure as : " + departure,
					"System failed to select departure as : " + departure);
			return false;
		}

	}

	public boolean select_DestinationCity(String destination) {

		boolean status = common.selectByValue(select_ToPort, "ToPortDropdown", destination);

		if (status) {
			ProLogger.info("System has successfully selected destination as : " + destination);
			ProLogger.logMyScreenshot(driver, "System should select destination as : " + destination,
					"System successfully selected departure as : " + destination);
			return true;

		} else {
			ProLogger.info("System has failed to select destination as : " + destination);
			ProLogger.logMyScreenshot(driver, "System should select destination as : " + destination,
					"System failed to select departure as : " + destination);
			return true;
		}

	}

	public boolean findFlights() {

		common.clickOnObject(findFlights, "Find Flights");
		boolean status = common.isElementPresent(flightHeaderMessage, "Choose Flight Page Header");
		if (status) {
			ProLogger.info("System has successfully clicked " + "Find Flights");
			ProLogger.logMyScreenshot(driver, "System has successfully selected " + "Find Flights",
					"System has successfully selected " + "Find Flights");
			return true;

		} else {
			ProLogger.info("System has failed to click " + "Find Flights");
			ProLogger.logMyScreenshot(driver, "System should click on " + "Find Flights",
					"System has failed to click " + "Find Flights");
			return true;
		}

	}

	public Travel_World_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		common = new Common(driver);

	}
}
