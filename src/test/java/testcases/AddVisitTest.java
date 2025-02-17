package testcases;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import pages.AddVisitPage;
import pages.LoginPage;
import utils.ConfigReader;

public class AddVisitTest extends TestBase {
    AddVisitPage addVisit;
    LoginPage login;
   
    // Helper method to log in using role-specific credentials from ConfigReader
    private void loginAs(String role) {
        driver.get(ConfigReader.getProperty("baseURL"));
        login = new LoginPage(driver);
        if(role.equalsIgnoreCase("manager")) {
            login.enterUsername(ConfigReader.getProperty("manager.username"));
            login.enterPassword(ConfigReader.getProperty("manager.password"));
        } else if(role.equalsIgnoreCase("associate")) {
            // For Associate test, we use Associate 2 credentials
            login.enterUsername(ConfigReader.getProperty("associate2.username"));
            login.enterPassword(ConfigReader.getProperty("associate2.password"));
        }
        login.clickLoginButton();
    }
    
    @Test(priority = 1)
    public void testAddVisitManager() {
        loginAs("manager");
        addVisit = new AddVisitPage(driver);
        
        Assert.assertTrue(addVisit.addvisit_displayed(), "Add visit button is not displayed");
        System.out.println("Add visit button is displayed");
        
        addVisit.add_visit();
        System.out.println("Add visit form opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        addVisit.select_inst();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // For Manager, select employee "Associate 1"
        addVisit.select_emp("Associate 1");
        
        addVisit.date_time();
        System.out.println("Date selected");
        addVisit.purpose();
        addVisit.contactPerson();
        addVisit.addNote();
        addVisit.dis_topic();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Upload file using full path
//        addVisit.upload_file("C:\\Users\\admin\\Desktop\\testfile.pdf");
        System.out.println("✅ File not added");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // (Optional: Submit visit and check pop-up)
        // addVisit.submit_visit();
        // if(addVisit.isPopupDisplayed()){
        //     System.out.println("✅ Success pop-up displayed");
        //     addVisit.popup_close();
        // }
        
        addVisit.cancel_visit();
        login = new LoginPage(driver);
        login.clickProfile();
        login.clickLogoutButton();
    }
    
    @Test(priority = 2)
    public void testAddVisitAssociate() {
        loginAs("associate");
        addVisit = new AddVisitPage(driver);
        
        Assert.assertTrue(addVisit.addvisit_displayed(), "Add visit button is not displayed");
        System.out.println("Add visit button is displayed");
        
        addVisit.add_visit();
        System.out.println("Add visit form opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        addVisit.select_inst();
        // For Associate, select employee "Associate 2"
        addVisit.select_emp("Associate 2");
        
        addVisit.date_time();
        System.out.println("Date selected");
        addVisit.purpose();
        System.out.println("Purpose selected");
        addVisit.contactPerson();
        System.out.println("Contact person selected");
        addVisit.addNote();
        System.out.println("Note added");
        addVisit.dis_topic();
        System.out.println("Topic selected");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Upload file (using a different path if required)
        //addVisit.upload_file("C:\\Users\\admin\\Desktop");
        System.out.println("✅ File not uploaded ");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        addVisit.cancel_visit();
        login = new LoginPage(driver);
        login.clickProfile();
        login.clickLogoutButton();
    }
}
