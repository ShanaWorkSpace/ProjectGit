package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import pages.AddInstitution;
import pages.LoginPage;
import utils.ConfigReader;

public class AddinsititutionTest extends TestBase {
    AddInstitution institution;
    LoginPage login;
    
    // Helper method to perform login based on role
    private void loginAs(String role) {
        driver.get(ConfigReader.getProperty("baseURL"));
        login = new LoginPage(driver);
        if (role.equalsIgnoreCase("manager")) {
            login.enterUsername(ConfigReader.getProperty("manager.username"));
            login.enterPassword(ConfigReader.getProperty("manager.password"));
        } else {
            // For associate test, you can use either associate1 or associate2 credentials
            login.enterUsername(ConfigReader.getProperty("associate1.username"));
            login.enterPassword(ConfigReader.getProperty("associate1.password"));
        }
        login.clickLoginButton();
    }
    
    @Test(priority = 1)
    public void addInstitutionAsManager() {
        addInstitutionAsRole("manager");
    }
    
    @Test(priority = 2)
    public void addInstitutionAsAssociate() {
        addInstitutionAsRole("associate");
    }
    
    // Reusable method for both roles
    public void addInstitutionAsRole(String role) {
        // Login using common method
        loginAs(role);
        
        institution = new AddInstitution(driver);
        
        // Open the Add Institutions form
        institution.btnAddInst();
        
        // Fill out the form
        institution.inst_type();
        institution.select_type(role);
        institution.inst_name();
        institution.inst_mail();
        institution.inst_place();
        institution.inst_state();
        institution.inst_dist();
        institution.inst_submit();
        
        // Verify that success pop-up is displayed and then close it
        Assert.assertTrue(institution.isDisplayed(), "Institution success message not displayed");
        institution.popupclose();
        
        // Logout based on role
        login = new LoginPage(driver);
        if (role.equalsIgnoreCase("manager")) {
            login.clickProfile();
        } else {
            login.clickProfile();
        }
        login.clickLogoutButton();
    }
}
