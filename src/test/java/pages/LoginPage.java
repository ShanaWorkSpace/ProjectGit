package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver
    public LoginPage(WebDriver drv) {
        this.driver = drv;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 5-second explicit wait
    }

    // Method to input username
    public void enterUsername(String username) {
        WebElement uname = driver.findElement(By.xpath("//input[@name='userId']"));
        uname.clear();
        uname.sendKeys(username);
    }

    // Method to input password
    public void enterPassword(String password) {
        WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
        pwd.clear();
        pwd.sendKeys(password);
    }

    // Click the visibility button (optional)
    public void viewButtonClick() {
        WebElement viewButton = driver.findElement(By.cssSelector("svg.h-6.text-gray-700.block"));
        viewButton.click();
    }

    // Click the login button
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        loginButton.click();
    }

    // Logout: Click profile icon and logout button
    public void clickProfile() {
        WebElement profile = driver.findElement(By.xpath("//img[contains(@src, '/profile/')]"));
        profile.click();
    }

    public void clickLogoutButton() {
        WebElement logoutButton = driver.findElement(By.xpath("//div[text()='Logout']"));
        logoutButton.click();
    }

    // Validate dashboard display for role verification
    public String verifyDashboardRole() {
        String[] xpaths = {
            "//header/div[2]/div[2]/p",
            "//div[1]/div[2]/div[2]/div[1]/p[2]",
            "//div[1]/div[2]/div[2]/div[2]/p[2]"
        };

        for (String xpath : xpaths) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                return element.getText(); 
            } catch (Exception e) {
                // Continue if element not found
            }
        }
        return "No matching element found!";
    }

    // Locate and return error messages
    public String locateErrorMessage() {
        String[] xpaths = {
            "//div/div[1]/div[2]",
            "//div[1]/div[2]/div[2]/div[1]/p[2]",
            "//div[1]/div[2]/div[2]/div[2]/p[2]"
        };

        for (String xpath : xpaths) {
            try {
                WebElement errorElement = driver.findElement(By.xpath(xpath));
                return errorElement.getText();
            } catch (Exception ignored) {
            }
        }
        return "No error message found!";
    }

    // Forgot password process
    public void forgotPassword() {
        WebElement forgot = driver.findElement(By.xpath("//p[text()='Forgotpassword?']"));
        forgot.click();
    }

    public boolean isEmailPopupDisplayed() {
        try {
            WebElement emailPopup = driver.findElement(By.xpath("//h3[text()='Forgot Your Password']"));
            return emailPopup.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void provideEmail(String email) {
        WebElement emailInput = driver.findElement(By.xpath("//input[@name='email']"));
        emailInput.sendKeys(email);
        WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton.click();
    }

    public boolean isSendMailPopupDisplayed() {
        try {
            WebElement popup = driver.findElement(By.xpath("//p[text()='If your email is registered with us, you will receive a password reset link shortly.']"));
            return popup.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void closePopup() {
        WebElement closeButton = driver.findElement(By.xpath("//button[text()='Close']"));
        closeButton.click();
    }

//    // Validate blank credentials messages
//    public String getBlankUserIdMessage() {
//        WebElement uname = driver.findElement(By.xpath("//p[text()='Please enter the user id']"));
//        return uname.getText();
//    }
//
//    public String getBlankPasswordMessage() {
//        WebElement pwd = driver.findElement(By.xpath("//p[text()='Please enter the password']"));
//        return pwd.getText();
//    }
}
