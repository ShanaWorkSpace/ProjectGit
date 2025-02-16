package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoginError;
import pages.LoginPage;
import utils.ConfigReader;

public class NegativeLoginTest extends TestBase {
    
    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] getInvalidLoginData() {
        return new Object[][]{
            // Each array: {username, password, expectedError substring}
            {ConfigReader.getProperty("manager.username"), "", "Please enter the password"},
            {"invalid@ictkerala.org", "wrongpass", "Invalid user Credentials"},
            {"", ConfigReader.getProperty("manager.password"), "Please enter the user id"},
            {"", "", "Please enter"}  // Expected substring present in both error messages
        };
    }

    @Test(dataProvider = "invalidLoginCredentials")
    public void negativeLoginTest(String username, String password, String expectedError) {
        // Instantiate LoginPage and perform login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        
        // Optional: wait briefly for error messages to appear.
        try {
            Thread.sleep(1000); // Consider replacing with a more robust explicit wait if needed.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Capture error messages using LoginError
        LoginError loginError = new LoginError(driver);
        String actualError = loginError.locateErrorMessage();
        
        // Debug output
        System.out.println("Attempted login with: username='" + username + "', password='" + password + "'");
        System.out.println("Expected error to contain: '" + expectedError + "'");
        System.out.println("Actual error message(s): '" + actualError + "'");
        
        // Assertion: Check if the actual error messages contain the expected error substring.
        Assert.assertTrue(actualError.contains(expectedError), "Error message verification failed!");
    }
}
