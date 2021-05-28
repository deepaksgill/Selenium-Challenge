package com.projectname.scripts;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.testng.annotations.Test;

import com.projectname.framework.Config;

public class BlazeDemo_Travel_World_Test extends BaseTest {

	String departure = Config.Departures[new Random().nextInt(Config.Departures.length)];
	String destination = Config.Destinations[new Random().nextInt(Config.Destinations.length)];

	@Test(description = "Verifying Landing to Travel The World")
	public void BlazeDemo_Travel_World_Test01() {
		assertEquals(prePageFactory.getTravel_World_Page().goTo_TravelTheWorld_Page(), true);
	}

	@Test(description = "Verifying selection of departure city", dependsOnMethods = { "BlazeDemo_Travel_World_Test01" })
	public void BlazeDemo_Travel_World_Test02() {
		assertEquals(prePageFactory.getTravel_World_Page().select_DepartureCity(departure), true);
	}

	@Test(description = "Verifying selection of destination city", dependsOnMethods = { "BlazeDemo_Travel_World_Test02" })
	public void BlazeDemo_Travel_World_Test03() {
		assertEquals(prePageFactory.getTravel_World_Page().select_DestinationCity(destination), true);
	}

	@Test(description = "Finding Flights", dependsOnMethods = { "BlazeDemo_Travel_World_Test03" })
	public void BlazeDemo_Travel_World_Test04() {
		assertEquals(prePageFactory.getTravel_World_Page().findFlights(), true);
	}

	@Test(description = "Verifying Landing to Choose Flight Page", dependsOnMethods = { "BlazeDemo_Travel_World_Test04" })
	public void BlazeDemo_Travel_World_Test05() {
		assertEquals(prePageFactory.getCheckFlights_Page().goTo_ChooseFlight_Page(), true);
	}

	@Test(description = "Verifying selection of any flight", dependsOnMethods = { "BlazeDemo_Travel_World_Test05" })
	public void BlazeDemo_CheckFlights_Test06() {
		assertEquals(prePageFactory.getCheckFlights_Page().verify_Dep_Des_In_Header(departure, destination), true);
	}

	@Test(description = "Verifying selection of destination city", dependsOnMethods = { "BlazeDemo_CheckFlights_Test06" })
	public void BlazeDemo_CheckFlights_Test07() {
		assertEquals(prePageFactory.getCheckFlights_Page().choose_Flight(), true);
	}
}
