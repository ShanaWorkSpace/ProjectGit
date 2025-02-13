package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.DashboardPage;
import pages.LoginPage;
import pages.VisitManagementPage;
import utils.ConfigReader;

public class VisitManagementTest extends TestBase {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    VisitManagementPage visitPage;

    @BeforeClass
    public void setupPages() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        visitPage = new VisitManagementPage(driver);
    }

    @Test
    public void addUpdateDeleteVisitTest() {
        // Step 1: Login as Manager
        loginPage.username(ConfigReader.getProperty("manager.username"));
        loginPage.password(ConfigReader.getProperty("manager.password"));
        loginPage.view_buttonclick();
       
        
        // Verify login successful
        String dashboardTitle = dashboardPage.getDashboardTitle();
        Assert.assertEquals(dashboardTitle, "Dashboard", "Login Failed!");

        // Step 2: Add a Visit
        boolean visitAdded = visitPage.addVisit("ABC University", "Academics", "2025-02-15", "Initial visit");
        Assert.assertTrue(visitAdded, "Visit not added!");

        // Step 3: Update the Visit
        boolean visitUpdated = visitPage.updateVisit("ABC University", "2025-02-20", "Rescheduled");
        Assert.assertTrue(visitUpdated, "Visit not updated!");

        // Step 4: Delete the Visit
        boolean visitDeleted = visitPage.deleteVisit("ABC University");
        Assert.assertTrue(visitDeleted, "Visit not deleted!");

        // Logout after test
        loginPage.outprofclick();
        loginPage.outbuttonclick();
    }
}
