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
		Assert.assertEquals(userRole, expectedRole , "Login role verification failed!");
		}
	
	@DataProvider(name = "invalidLoginCredentials")
    public Object[][] getInvalidLoginData() {
        return new Object[][]{
            {ConfigReader.getProperty("manager.username"), "", ""},
            {"invalid@ictkerala.org", "wrongpass", ""},
            {"", ConfigReader.getProperty("manager.password"), ""},
            {"", "", ""}
        };
    }

    @Test(dataProvider = "invalidLoginCredentials")
    public void negativeLoginTest(String username, String password, String expectedError) {
    	Obj.username(username);
		Obj.password(password);
		Obj.view_buttonclick();
		// Capture the error message immediately after the first invalid login attempt
		 // Small wait to allow the error message to appear
	    try {
	        Thread.sleep(1000);  // Temporary fix to check if timing is the issue
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
        String actualError = Obj.locateErrorMessage();
        
     // Debugging: Print actual error message before assertion
        System.out.println("Expected Error: " + expectedError);
        System.out.println("Actual Error: " + actualError);

        // Verify if the actual error contains the expected error
        Assert.assertTrue(actualError.contains(expectedError), "Error message verification failed!");
    } 
//   
}