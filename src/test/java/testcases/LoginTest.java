package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;

import utils.ConfigReader;

public class LoginTest extends TestBase {
	LoginPage Obj;
	
	@BeforeClass
	public void objInt()
	{
		Obj=new LoginPage(driver);
	}
	
	@DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() {
        return new Object[][]{
        	{ConfigReader.getProperty("manager.username"), ConfigReader.getProperty("manager.password"), "Manager 1"},
            {ConfigReader.getProperty("associate1.username"), ConfigReader.getProperty("associate1.password"), "Associate 1"},
            {ConfigReader.getProperty("associate2.username"), ConfigReader.getProperty("associate2.password"), "Associate 2"},
            
            {"invalid@ictkerala.org", "wrongpass", "Invalid User"},
            {"", ConfigReader.getProperty("manager.password"), "Empty Username"},
            {ConfigReader.getProperty("manager.username"), "", "Empty Password"},
            {"", "", "Both Empty"}
        };
    }
	
	@Test(dataProvider = "loginCredentials")
	public void firsttest(String username, String password, String expectedRole) 
	{
		Obj.username(username);
		Obj.password(password);
		Obj.view_buttonclick();
		
		String userRole=Obj.Dashcheck();
		Obj.outprofclick();
		Obj.outbuttonclick();
		System.out.println(userRole);
		System.out.println(expectedRole);
//		Assert.assertEquals(userRole, expectedRole , "Login role verification failed!");
		}

}