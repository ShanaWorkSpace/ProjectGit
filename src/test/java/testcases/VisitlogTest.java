package testcases;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoginPage;
import pages.VisitLogs;
import utils.ConfigReader;

public class VisitlogTest extends TestBase {
    VisitLogs visit;
    LoginPage login;
    
    // Helper method to log in using role-specific credentials.
    private void loginAs(String role) {
        // Refresh the page to ensure the login form is loaded
        driver.get(ConfigReader.getProperty("baseURL"));
        login = new LoginPage(driver);
        if(role.equalsIgnoreCase("manager")) {
            login.enterUsername(ConfigReader.getProperty("manager.username"));
            login.enterPassword(ConfigReader.getProperty("manager.password"));
        } else if(role.equalsIgnoreCase("associate")) {
            login.enterUsername(ConfigReader.getProperty("associate1.username"));
            login.enterPassword(ConfigReader.getProperty("associate1.password"));
        }
        login.clickLoginButton();
    }
    
    @Test(priority = 1)
    public void visitlogAsManager() {
        loginAs("manager");  // Login as manager using the helper method
        visit = new VisitLogs(driver);
        
        // Click on Visit Logs and verify page display
        visit.click_logs();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue(visit.isDisplayed(), "Visit Logs page is not displayed");
        System.out.println("Manager Visit Logs loaded properly");

        // Click on Export CSV and verify CSV download
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        visit.clickExportCSV();
        Assert.assertTrue(visit.isCSVDownloaded(), "CSV file was not downloaded!");
        System.out.println("CSV download successful");

        // Click on Add Visit to trigger visit creation
        visit.add_visit();
        System.out.println("Add visit done");
        
        // Logout using Loginpage methods
        login = new LoginPage(driver);
        login.clickProfile();
        login.clickLogoutButton();
    }
    
    @Test(priority = 2)
    public void visitlogAsAssociate() {
        loginAs("associate");  // Login as associate using the helper method
        visit = new VisitLogs(driver);
        
        // Click on Visit Logs and verify page display
        visit.click_logs();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue(visit.isDisplayed(), "Visit Logs page is not displayed");
        System.out.println("Associate Visit Logs loaded properly");

        // Click on Export CSV and verify CSV download
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        visit.clickExportCSV();
        Assert.assertTrue(visit.isCSVDownloaded(), "CSV file was not downloaded!");
        System.out.println("CSV download successful");
        
        // Logout using associate-specific logout actions
        login = new LoginPage(driver);
        login.clickProfile();
        login.clickLogoutButton();
    }
}
