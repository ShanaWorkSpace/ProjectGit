package testcases;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfileView;
import utils.ConfigReader;

public class ProfileViewTest extends TestBase {
    
    ProfileView pro;
    LoginPage login;
    
    // Helper method for logging in using role-specific credentials
    private void loginAs(String role) {
        login = new LoginPage(driver);
        if (role.equalsIgnoreCase("manager")) {
            login.enterUsername(ConfigReader.getProperty("manager.username"));
            login.enterPassword(ConfigReader.getProperty("manager.password"));
        } else if (role.equalsIgnoreCase("associate")) {
            // Adjust the property if you wish to test a different associate
            login.enterUsername(ConfigReader.getProperty("associate1.username"));
            login.enterPassword(ConfigReader.getProperty("associate1.password"));
        }
        login.clickLoginButton();
    }
    
    @Test(priority = 1)
    public void testProfileViewManager() {
        loginAs("manager");  // Login as manager using the helper method
        
        pro = new ProfileView(driver);
        login = new LoginPage(driver);  // Reinitialize if necessary
        
        // Open the manager profile
        boolean profileVisible = pro.profileManager();
        pro.click_Profile();
        boolean detailsDisplayed = pro.isDisplayed();
        
        // Assert that profile loaded properly
        Assert.assertTrue(profileVisible && detailsDisplayed, "Manager profile did not load properly");
        System.out.println("Manager profile loaded successfully");
        
        // Reset password for manager profile
        pro.resetPassword("Managertest@123", "NewPassword@2024");
        
        // Logout using Loginpage methods
        login.clickProfile();
        login.clickLogoutButton();
    }
    
    @Test(priority = 2)
    public void testProfileViewAssociate() {
        loginAs("associate");  // Login as associate using the helper method
        
        pro = new ProfileView(driver);
        login = new LoginPage(driver);  // Reinitialize if necessary
        
        // Execute associate profile flow
        pro.profileAssociate();
        
        // Logout using associate-specific actions from Loginpage
        login.clickProfile();
        login.clickLogoutButton();
    }
}
