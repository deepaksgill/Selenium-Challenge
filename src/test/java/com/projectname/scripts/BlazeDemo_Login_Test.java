package com.projectname.scripts;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class BlazeDemo_Login_Test extends BaseTest {
	
	
	@Test(description = "Navigate To BlazeDemo Login Page")
	public void BlazeDemo_Login_Test01() {
		assertEquals(prePageFactory.getBlazeDemo_Home_Page().navigate_To_BlazeDemo_HomePage(), true);
		assertEquals(prePageFactory.getBlazeDemo_Home_Page().navigate_To_BlazeDemo_LoginPage(), true);
		
	}
	
}