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
	
	@DataProvider(name = "validLoginCredentials")
    public Object[][] getValidLoginData() {
        return new Object[][]{
            {"manager1@ictkerala.org", "@manager#952", "Manager 1"},
            {"test2@ictkerala.org", "@test#952", "Associate 1"},
            {"test1@ictkerala.org", "@test#951", "Associate 2"}
        };
    }
	
	@DataProvider(name = "invalidLoginCredentials")
    public Object[][] getInvalidLoginData() {
        return new Object[][]{
            {"manager3@ictkerala.org", "@manager#953", "Invalid User Credentials"},
            {"test4@ictkerala.org", "", "Please enter the password"},
            {"", "@test#954", "Please enter the user id"},
            {"*********@ictkerala.org", "@*****#967", "Invalid User Credentials"},
            {"", "", "Please enter the user id"}
        };
    }


	
	 @Test(dataProvider = "validLoginCredentials", priority = 1)
	 public void testValidLogin(String username, String password, String expectedRole) {
	        Obj.username(username);
	        Obj.password(password);
	        Obj.view_buttonclick();
	        
	        String userRole = Obj.Dashcheck();
	        Obj.outprofclick();
	        Obj.outbuttonclick();
	        
	        System.out.println("Actual Role: " + userRole);
	        System.out.println("Expected Role: " + expectedRole);
	        
	        Assert.assertEquals(userRole, expectedRole, "Login role verification failed!");
	    }
	 
	 @Test(dataProvider = "invalidLoginCredentials", priority = 2)
	    public void testInvalidLogin(String username, String password, String expectedMessage) {
	        Obj.username(username);
	        Obj.password(password);
	        Obj.view_buttonclick();
	        
	        // Assuming an alert or message is displayed upon failure
	        String actualMessage = ""; // Implement a method in LoginPage to capture error message
	        try {
	            actualMessage = Obj.getErrorMessage(); // Implement this method in LoginPage
	        } catch (Exception e) {
	            actualMessage = "No error message displayed";
	        }
	        
	        System.out.println("Actual Message: " + actualMessage);
	        System.out.println("Expected Message: " + expectedMessage);
	        
	        Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch for invalid login!");
	    }
	}

}