package com.projectname.scripts;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class BlazeDemo_Home_Test extends BaseTest {
	
	
	@Test(description = "Navigate To BlazeDemo Home Page")
	public void BlazeDemo_Home_Test01() {
		assertEquals(prePageFactory.getBlazeDemo_Home_Page().navigate_To_BlazeDemo_HomePage(), true);
	}
	
	@Test(description = "Navigate To Registration Page" , dependsOnMethods = { "BlazeDemo_Home_Test01" })
	public void BlazeDemo_Home_Test02() {
		assertEquals(prePageFactory.getBlazeDemo_Home_Page().goToRegisterPage(), true);
	}
	
	@Test(description = "Fill The Registration Page", dependsOnMethods = { "BlazeDemo_Home_Test02" })
	public void BlazeDemo_Home_Test03() {
		assertEquals(prePageFactory.getBlazeDemo_Home_Page().register_User(), true);
	}

}
