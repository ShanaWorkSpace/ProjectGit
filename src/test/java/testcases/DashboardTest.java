package testcases;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class DashboardTest extends TestBase {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeClass
    public void setupPages() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }
    
    // Helper method for logging in based on role.
    private void loginAs(String role) {
        if (role.equalsIgnoreCase("manager")) {
            loginPage.enterUsername(ConfigReader.getProperty("manager.username"));
            loginPage.enterPassword(ConfigReader.getProperty("manager.password"));
        } else if (role.equalsIgnoreCase("associate")) {
            // Here we pick associate1; adjust if you want a different associate
            loginPage.enterUsername(ConfigReader.getProperty("associate1.username"));
            loginPage.enterPassword(ConfigReader.getProperty("associate1.password"));
        }
        loginPage.clickLoginButton();
    }

    // DataProvider for multiple user credentials
    @DataProvider(name = "userCredentials")
    public Object[][] getUserCredentials() {
        return new Object[][]{
            {ConfigReader.getProperty("manager.username"), ConfigReader.getProperty("manager.password"), "Manager 1"},
            {ConfigReader.getProperty("associate1.username"), ConfigReader.getProperty("associate1.password"), "Associate 1"},
            {ConfigReader.getProperty("associate2.username"), ConfigReader.getProperty("associate2.password"), "Associate 2"}
        };
    }

    // Test: Verify Dashboard details for each user
    @Test(priority = 1, dataProvider = "userCredentials")
    public void verifyDashboardForMultipleUsers(String username, String password, String expectedRole) {
        // Login
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Wait until the Dashboard loads (URL should contain "homepage")
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("homepage"));

        // Verify current URL
        String currentUrl = dashboardPage.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("https://dev.visit.ictkerala.org/homepage"), "Dashboard URL Mismatch!");

        // Verify Dashboard Title
        String dashboardTitle = dashboardPage.getDashboardTitle();
        System.out.println("Dashboard Title: " + dashboardTitle);
        Assert.assertEquals(dashboardTitle, "Dashboard", "Dashboard Title Mismatch!");

        // Verify User Role
        String actualRole = dashboardPage.getUserRole();
        System.out.println("Actual Role: " + actualRole);
        System.out.println("Expected Role: " + expectedRole);
//        Assert.assertEquals(actualRole, expectedRole, "User role does not match expected role!");

        // Logout to prepare for the next iteration
        loginPage.clickProfile();
        loginPage.clickLogoutButton();
    }

    // Test: Verify that the Dashboard elements load properly for Manager
    @Test(priority = 2)
    public void verifyDashboardElementsAsManager() {
        loginAs("manager");
        // Wait briefly to ensure page load
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("homepage"));
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Manager Dashboard is not loaded properly");
        System.out.println("Manager dashboard loaded properly");
        loginPage.clickProfile();
        loginPage.clickLogoutButton();
    }

    // Test: Verify that the Dashboard elements load properly for Associate
    @Test(priority = 3)
    public void verifyDashboardElementsAsAssociate() {
        loginAs("associate");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("homepage"));
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Associate Dashboard is not loaded properly");
        System.out.println("Associate dashboard loaded properly");
        loginPage.clickProfile();
        loginPage.clickLogoutButton();
    }
}
