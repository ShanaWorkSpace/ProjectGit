package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends TestBase {
    
    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String userType) {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://dev.visit.ictkerala.org/");
        
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();

        // Verify successful login
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Dashboard')]"))
                .isDisplayed(), "Login failed for: " + username);

        System.out.println(userType + " login successful: " + username);

        // Logout after successful login
        driver.findElement(By.xpath("//button[text()='Logout']")).click();
    }
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        ConfigReader config = new ConfigReader();
        return new Object[][] {
            { config.getProperty("manager.username"), config.getProperty("manager.password"), "Manager" },
            { config.getProperty("associate1.username"), config.getProperty("associate1.password"), "Associate 1" },
            { config.getProperty("associate2.username"), config.getProperty("associate2.password"), "Associate 2" }
        };
    }
}
