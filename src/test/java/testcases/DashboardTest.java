package testcases;

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

    // DataProvider for login credentials
    @DataProvider(name = "userCredentials")
    public Object[][] getUserCredentials() {
        return new Object[][]{
            {ConfigReader.getProperty("manager.username"), ConfigReader.getProperty("manager.password"), "Manager 1"},
            {ConfigReader.getProperty("associate1.username"), ConfigReader.getProperty("associate1.password"), "Associate 1"},
            {ConfigReader.getProperty("associate2.username"), ConfigReader.getProperty("associate2.password"), "Associate 2"}
        };
    }

    @Test(dataProvider = "userCredentials")
    public void verifyDashboardForMultipleUsers(String username, String password, String expectedRole) {
        // Step 1: Login
    	loginPage.username(username);
        loginPage.password(password);
        loginPage.view_buttonclick();
        
        
        // Step 2: Verify the Current URL
        String currentUrl = dashboardPage.getCurrentUrl();
        System.out.println(currentUrl);
//      Assert.assertTrue(currentUrl.contains("https://dev.visit.ictkerala.org/"), "Dashboard URL Mismatch!");

        // Step 3: Verify Dashboard Title
        String dashboardTitle = dashboardPage.getDashboardTitle();
        System.out.println(dashboardTitle);
        Assert.assertEquals(dashboardTitle, "Dashboard", "Dashboard Title Mismatch!");
     

        // Step 4: Verify User Role
        String actualRole = dashboardPage.getUserRole();
        Assert.assertEquals(actualRole, expectedRole, "User role does not match expected role!");

        // Step 5: Capture Initial Counts
        String initialTotalVisits = dashboardPage.getTotalVisits();
        String initialUpcomingVisits = dashboardPage.getUpcomingVisits();
        String initialOrgStats = dashboardPage.getOrganizationalStats();
        String initialVisitView = dashboardPage.getVisitView();

        // Step 6: Mock Visit Add (You should implement this in VisitManagementPage)
        driver.navigate().refresh();
        String newTotalVisits = dashboardPage.getTotalVisits();
        String newUpcomingVisits = dashboardPage.getUpcomingVisits();
        Assert.assertNotEquals(newTotalVisits, initialTotalVisits, "Total Visits not updated!");
        Assert.assertNotEquals(newUpcomingVisits, initialUpcomingVisits, "Upcoming Visits not updated!");

        // Step 7: Mock Visit Delete
        driver.navigate().refresh();
        String updatedTotalVisits = dashboardPage.getTotalVisits();
        Assert.assertNotEquals(updatedTotalVisits, newTotalVisits, "Total Visits not updated after deletion!");

        // Step 8: Mock Visit Update
        driver.navigate().refresh();
        String updatedVisitView = dashboardPage.getVisitView();
        Assert.assertNotEquals(updatedVisitView, initialVisitView, "Visit View count not updated!");

        // Step 9: Verify Notifications
        boolean isNotificationOpened = dashboardPage.openNotification();
        Assert.assertTrue(isNotificationOpened, "Notification did not open!");

        boolean isNotificationClosed = dashboardPage.closeNotification();
        Assert.assertTrue(isNotificationClosed, "Notification did not close!");
        
        // Logout and move to next user
           loginPage.outprofclick();
           loginPage.outbuttonclick();
       }
        
    }

