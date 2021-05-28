package com.projectname.framework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {

	Driver d;

	// *****************************************************************************************
	// * Name : fGetWebDriver
	// * Description : Returns the required webdriver
	// * Input Params : None
	// * Return Values : WebDriver
	// *****************************************************************************************
	public WebDriver fGetWebDriver(String driverType )  {
		
		if (driverType.contains("CHROME")) {
			
			WebDriver driver = null;
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--disable-extensions");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");

			driver = new ChromeDriver(options);
			
			return driver;

		}

		else if (driverType.contains("FIREFOX")) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM authentication_32bit.exe");
				Runtime.getRuntime().exec("taskkill /F /IM authentication_64bit.exe");

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
		
			WebDriver driver = null;
			try {
				driver = new FirefoxDriver();
			} catch (Exception e) {
				System.out.println(e);
			}
			return driver;
		}

		 else if (driverType.contains("IE")) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				Runtime.getRuntime().exec("taskkill /F /IM authenticationIE32.exe");
				Runtime.getRuntime().exec("taskkill /F /IM authenticationIE64.exe");
				Process p = Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
				
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/exe/IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("nativeEvents", false);
			return new InternetExplorerDriver(caps);
		}

		else {
			System.out.println("Driver type " + driverType + " is invalid");
			return null;
		}
	}
}
