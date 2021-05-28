package com.projectname.framework;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

/**
 * 
 * 
 * This class contains all the common methods for common selenium events which
 * can be reused through out the project.
 * 
 * 
 */

public class Common {

	private WebDriver driver;
	JavascriptExecutor js;

	public Common(WebDriver driver2) {
		driver = driver2;
	}

	// Function :- wait_ForElementToBe_Visible
	// This function will wait for element to be Visible

	public boolean wait_ForElementToBe_Visible(WebElement element, String ele_Name) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(Config.ewait))
					.pollingEvery(Duration.ofMillis(Config.iwait)).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOf(element));

			ProLogger.info("System has successfully displayed element named : " + ele_Name);
			return true;
		}

		catch (Exception e) {

			ProLogger.info("System has failed to display element named : " + ele_Name + " because of " + e.toString());
			return false;
		}

	}

	// Function :- wait_ForElementToBe_Clickable
	// This function will wait for element to be Clickable

	public boolean wait_ForElementToBe_Clickable(WebElement element, String ele_Name) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(Config.ewait))
					.pollingEvery(Duration.ofMillis(Config.iwait)).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			ProLogger.info("System has successfully made element to click named : " + ele_Name);
			return true;

		} catch (Exception e) {
			ProLogger.info(
					"System has failed to make element to click named : " + ele_Name + " because of " + e.toString());
			return false;
		}
	}

	// Function :- clickOnObject
	// This function will work for all click operations for all WebElements

	public boolean clickOnObject(WebElement element, String ele_Name) {
		try {
			wait_ForElementToBe_Clickable(element, ele_Name);

			element.click();
			ProLogger.info("System has successfully made element to click named : " + ele_Name);
			return true;

		} catch (Exception e) {
			ProLogger.info(
					"System has failed to make element to click named : " + ele_Name + " because of " + e.toString());
			return false;
		}

	}

	// Function :- clickOnObject_ByJS
	// This function will work for all JS click operations

	public boolean clickOnObject_ByJS(WebElement element, String ele_Name) {
		try {
			wait_ForElementToBe_Clickable(element, ele_Name);

			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			ProLogger.info(ele_Name + " is clicked successfully");
			return true;

		} catch (Exception exception) {
			ProLogger.info(ele_Name + "is not clicked because of : " + exception.toString());
			return false;
		}

	}

	public boolean selectByVisibleText(WebElement select_Element, String ele_Name, String objValue) {
		boolean selectElement = false;
		boolean isSelectDone = false;

		selectElement = wait_ForElementToBe_Clickable(select_Element, ele_Name);

		try {
			if (selectElement) {
				Select select = new Select(select_Element);
				select.selectByVisibleText(objValue);
				ProLogger.info(
						"System has successfully selected " + objValue + "from Select element named: " + ele_Name);
				isSelectDone = true;
			}
		} catch (Exception exception) {
			ProLogger.info("System has failed to select " + objValue + " from Select element named: " + ele_Name);
			isSelectDone = false;
		}
		return isSelectDone;
	}

	public boolean selectByValue(WebElement select_Element, String ele_Name, String objValue) {
		boolean selectElement = false;
		boolean isSelectDone = false;

		selectElement = wait_ForElementToBe_Clickable(select_Element, ele_Name);

		try {
			if (selectElement) {

				Select select = new Select(select_Element);
				select.selectByValue(objValue);
				ProLogger.info(
						"System has successfully selected " + objValue + "from Select element named: " + ele_Name);
				isSelectDone = true;
			}
		} catch (Exception exception) {
			ProLogger.info("System has failed to select " + objValue + " from Select element named: " + ele_Name);
			isSelectDone = false;
		}
		return isSelectDone;
	}

	public void loadPage(int millis) {
		try {
			Thread.sleep(millis);
			System.out.println("System has waited for " + millis + " millis");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean enterData(WebElement element, String aData, String ele_Name) {
		wait_ForElementToBe_Visible(element, ele_Name);

		try {
			clickOnObject(element, ele_Name);
			element.sendKeys(aData);
			String eData = getElementValue(element, ele_Name);
			assertEquals(eData, aData);
			ProLogger.info("System has successfully entered data : " + aData + " into " + ele_Name);
			return true;
		} catch (Exception e) {
			ProLogger.info("System is unable to entered data into " + ele_Name);
			return false;
		}
	}

	public String getElementText(WebElement element, String ele_Name) {
		wait_ForElementToBe_Clickable(element, ele_Name);
		String text = null;

		try {
			text = element.getText();
			if (text != null)
				ProLogger.info("System has successfully fetched data for element named : " + ele_Name);
			return text;
		} catch (Exception e) {
			ProLogger.info("System has failed to fetch data for element named : " + ele_Name);
			return null;
		}
	}

	public boolean isElementPresent(WebElement element, String ele_Name) {
		try {
			wait_ForElementToBe_Clickable(element, ele_Name);

			element.click();
			ProLogger.info("System has successfully made element to click named : " + ele_Name);
			return true;

		} catch (Exception e) {
			ProLogger.info(
					"System has failed to make element to click named : " + ele_Name + " because of " + e.toString());
			return false;
		}

	}

	public String getElementValue(WebElement element, String ele_Name) {
		String valueExtracted = null;
		try {
			wait_ForElementToBe_Clickable(element, ele_Name);

			clickOnObject(element, ele_Name);
			valueExtracted = element.getAttribute("value");
			ProLogger.info("System has successfully extracted value from element named : " + ele_Name);
			return valueExtracted;

		} catch (Exception e) {
			ProLogger.info("System has failed to extract value from element named : " + ele_Name);
			return valueExtracted;
		}

	}

	public void waitOnElement(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}