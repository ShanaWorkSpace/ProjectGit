package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoginPage;
import pages.UpdateVisit;
import utils.ConfigReader;

public class UpdateVisitTest extends TestBase {
    LoginPage login;
    UpdateVisit visit;
    
    // Helper method to log in as a specific role.
    // Refresh the login page before entering credentials.
    private void loginAs(String role) {
        // Refresh the page to ensure the login form is loaded
        driver.get(ConfigReader.getProperty("baseURL"));
        login = new LoginPage(driver);
        if(role.equalsIgnoreCase("manager")){
            login.enterUsername(ConfigReader.getProperty("manager.username"));
            login.enterPassword(ConfigReader.getProperty("manager.password"));
        } else if(role.equalsIgnoreCase("associate")){
            login.enterUsername(ConfigReader.getProperty("associate1.username"));
            login.enterPassword(ConfigReader.getProperty("associate1.password"));
        }
        login.clickLoginButton();
    }
    
    @Test(priority = 1)
    public void verifyVisitManager() {
        loginAs("manager");
        visit = new UpdateVisit(driver);
        
        // Verify Upcoming Visits section is displayed properly
        Assert.assertTrue(visit.isdisplayed(), "Upcoming Visit field is not proper");
        System.out.println("Upcoming visit loaded properly");
        
        // Verify that the view details popup displays properly
        Assert.assertTrue(visit.viewdetailsDisplay(), "View Details popup is not proper");
        System.out.println("View Details popup loaded properly");
        
        // Close the view details popup
        visit.viewdataClose();
        
        // Click on update action and verify the update form displays
        visit.actionUpdate();
        Assert.assertTrue(visit.updateformDisplay(), "Update form is not proper");
        System.out.println("Update form loaded properly");
        
        // Submit the update and verify success popup
        visit.update();
        Assert.assertTrue(visit.updateSuccess(), "Update success popup is not proper");
        System.out.println("Update success popup loaded properly");
        
        // Close the update success popup
        visit.updateClose();
        
        // Logout using Loginpage methods
        login = new LoginPage(driver);
        login.clickProfile();
        login.clickLogoutButton();
    }
    
    @Test(priority = 2)
    public void verifyVisitAssociate() {
        loginAs("associate");
        visit = new UpdateVisit(driver);
        
        // Verify Upcoming Visits section is displayed properly
        Assert.assertTrue(visit.isdisplayed(), "Upcoming Visit field is not proper");
        System.out.println("Upcoming visit loaded properly");
        
        // Verify that the view details popup displays properly
        Assert.assertTrue(visit.viewdetailsDisplay(), "View Details popup is not proper");
        System.out.println("View Details popup loaded properly");
        
        // Close the view details popup
        visit.viewdataClose();
        
        // Logout using associate-specific logout actions
        login = new LoginPage(driver);
        login.clickProfile();
        login.clickLogoutButton();
    }
}
