package com.projectname.pages;

import org.openqa.selenium.WebDriver;

public class Project_PageFactory {

	WebDriver driver;

	Project_PageFactory prePageFactory;

	Travel_World_Page travel_World_Page;
	Check_Flights_Page check_Flights_Page;
	BlazeDemo_Home_Page homePage_BlazeDemo;

	public Project_PageFactory(WebDriver driver) {
		this.driver = driver;
		travel_World_Page = new Travel_World_Page(driver);
		check_Flights_Page = new Check_Flights_Page(driver);
		homePage_BlazeDemo = new BlazeDemo_Home_Page(driver);
	}

	public Travel_World_Page getTravel_World_Page() {
		return travel_World_Page;
	}

	public Check_Flights_Page getCheckFlights_Page() {
		return check_Flights_Page;
	}

	public BlazeDemo_Home_Page getBlazeDemo_Home_Page() {
		return homePage_BlazeDemo;
	}

}
