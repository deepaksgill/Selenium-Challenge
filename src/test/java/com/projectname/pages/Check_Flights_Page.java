package com.projectname.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.projectname.framework.Common;
import com.projectname.framework.ProLogger;

public class Check_Flights_Page {

	WebDriver driver;
	Common common;

	@FindBy(how = How.CSS, using = "div.container > h3")
	private WebElement flightHeaderMessage;

	@FindBy(how = How.CSS, using = "tr > td > input")
	private List<WebElement> chooseFlight;

	@FindBy(how = How.CSS, using = "body > div.container > h2")
	private WebElement reservedFlightHeader;

	public boolean goTo_ChooseFlight_Page() {

		boolean status = common.isElementPresent(flightHeaderMessage, "Choose Flight Page Header");
		try {
			assertEquals(status, true);
			ProLogger.info("System has successfully navigated to \"Choose Flights Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"Choose Flights Page\"",
					"System successfully navigated to \"Choose Flights Page\"");
			return true;

		} catch (Exception e) {

			ProLogger.info("System has failed to navigate to \"Choose Flights Page\"");
			ProLogger.logMyScreenshot(driver, "System should navigate to \"Choose Flights Page\"",
					"System failed to navigate to \"Choose Flights Page\"");
			return true;
		}

	}

	public boolean verify_Dep_Des_In_Header(String departure, String destination) {

		String headerMessage = common.getElementText(flightHeaderMessage, "flightHeaderMessage");
		boolean validCities;
		String[] messageTokens = headerMessage.replace("Flights from ", "").replace(":", "").split("to");

		validCities = departure.equals(messageTokens[0].trim()) && destination.equals(messageTokens[1].trim());

		if (validCities) {
			ProLogger.info("System has successfully navigated to the correct Flight Selection Page ");
			ProLogger.logMyScreenshot(driver, "System should navigate to the correct Flight Selection Page",
					"System has successfully navigated to the correct Flight Selection Page ");
			return true;

		} else {
			ProLogger.info("System has failed to navigate to the correct Flight Selection Page");
			ProLogger.logMyScreenshot(driver, "System should navigate to the correct Flight Selection Page",
					"System failed to navigate to the correct Flight Selection Page");
			return false;
		}

	}

	public boolean choose_Flight() {

		WebElement element = chooseFlight.get(new Random().nextInt(chooseFlight.size()));
		common.clickOnObject(element, "Flight Selection");
		String resFlightHeader = common.getElementText(reservedFlightHeader, "reservedFlightHeader");

		if (resFlightHeader.contains("has been reserved")) {
			ProLogger.info("System has successfully choosen flight and moved to Reserved Page ");
			ProLogger.logMyScreenshot(driver, "System should choose flight and moved to Reserved Page ",
					"System successfully choosen flight and moved to Reserved Page ");
			return true;

		} else {
			ProLogger.info("System has failed to choose flight and moved to Reserved Page");
			ProLogger.logMyScreenshot(driver, "System should choose flight and moved to Reserved Page",
					"System failed to choose flight and moved to Reserved Page");
			return true;
		}

	}

	public Check_Flights_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		common = new Common(driver);
	}
}
