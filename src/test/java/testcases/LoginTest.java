package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import utils.ConfigReader;
import utils.AutomationConstants;

public class LoginTest extends TestBase {
    LoginPage obj;

    @BeforeClass
    public void setupObjects() {
        obj = new LoginPage(driver);
    }

    @DataProvider(name = "validLoginCredentials")
    public Object[][] getLoginData() {
        return new Object[][]{
            {ConfigReader.getProperty("manager.username"), ConfigReader.getProperty("manager.password"), "Manager 1"},
            {ConfigReader.getProperty("associate1.username"), ConfigReader.getProperty("associate1.password"), "Associate 1"},
            {ConfigReader.getProperty("associate2.username"), ConfigReader.getProperty("associate2.password"), "Associate 2"},
        };
    }

    @Test(priority = 1, dataProvider = "validLoginCredentials")
    public void loginTest(String username, String password, String expectedRole) {
        obj.enterUsername(username);
        obj.enterPassword(password);
        obj.clickLoginButton();

        String userRole = obj.verifyDashboardRole();
        obj.clickProfile();
        obj.clickLogoutButton();

        Assert.assertEquals(userRole, expectedRole, "Login role verification failed!");
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

    @Test(priority = 2, dataProvider = "invalidLoginCredentials")
    public void negativeLoginTest(String username, String password, String expectedError) {
        obj.enterUsername(username);
        obj.enterPassword(password);
        obj.clickLoginButton();

        String actualError = obj.locateErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError), "Error message verification failed!");
    }

    @Test(priority = 3)
    public void forgotPasswordTest() {
        obj.forgotPassword();
        Assert.assertTrue(obj.isEmailPopupDisplayed(), "Forgot password popup not displayed!");

        obj.provideEmail("dummymail@gmail.com");
        Assert.assertTrue(!obj.isSendMailPopupDisplayed(), "");

        obj.closePopup();
    }

//    @Test(priority = 4)
//    public void blankCredentialsTest() {
//        obj.clickLoginButton();
//
//        String actualUserIdError = obj.getBlankUserIdMessage();
//        Assert.assertEquals(actualUserIdError, AutomationConstants.userId);
//
//        String actualPasswordError = obj.getBlankPasswordMessage();
//        Assert.assertEquals(actualPasswordError, AutomationConstants.password);
//    }
}
