package com.projectname.framework;

import java.io.File;

public interface Config {

	String blaze_Url = "https://blazedemo.com/";
	String blaze_Header = "Welcome to the Simple Travel Agency!";

	String Departures[] = { "Paris", "Philadelphia", "Boston", "Portland", "San Diego" };
	String Destinations[] = { "Buenos Aires", "Rome", "London", "Berlin", "New York" };
	String UserDetails[] = { "TestUser", "TestCompany", "TestEmail", "TestPassword", "TestPassword" };

	int ewait = 5000;
	int iwait = 250;
	int waitTime = 200000;
	String reports_path = System.getProperty("user.dir") + File.separator + "reports";
	String Screenshots_Path = System.getProperty("user.dir") + File.separator + "target" + "screenshots";

}
