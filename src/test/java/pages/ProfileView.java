package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfileView {
    WebDriver driver;
    LoginPage login;
    
    public ProfileView(WebDriver drv) {
        this.driver = drv;
        this.login = new LoginPage(driver);
    }

    // Verify if Manager Profile is displayed by clicking the profile icon first
    public boolean profileManager() {
        login.clickProfile();  // Clicking profile
        try {
            WebElement profile = driver.findElement(By.xpath("//div[text()='Profile']"));
            return profile.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Click the Profile button from the menu
    public void click_Profile() {
        WebElement profile = driver.findElement(By.xpath("//div[text()='Profile']"));
        profile.click();
    }

    // Verify that Profile Details are Displayed
    public boolean isDisplayed() {
        try {
            return driver.findElement(By.xpath("//p[text()='Name']")).isDisplayed() &&
                   driver.findElement(By.xpath("//input[@type='text' and @disabled and @value='Manager 1']")).isDisplayed() &&
                   driver.findElement(By.xpath("//p[text()='Department']")).isDisplayed() &&
                   driver.findElement(By.xpath("//input[@type='text' and @disabled and @value='Academic Relations (Test)']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Reset Password using the provided old and new passwords
    public void resetPassword(String oldPassword, String newPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement oldPass = wait.until(ExpectedConditions.elementToBeClickable(By.id("previousPassword")));
        oldPass.sendKeys(oldPassword);

        WebElement newPass = driver.findElement(By.id("newPassword"));
        newPass.sendKeys(newPassword);

        WebElement confirmPass = driver.findElement(By.id("confirmPassword"));
        confirmPass.sendKeys(newPassword);

        WebElement updateBtn = driver.findElement(By.xpath("//button[text()='Update']"));
        updateBtn.click();
    }

    // For associate profile actions, using the manager flow for now
    public void profileAssociate() {
        profileManager();
        click_Profile();
        isDisplayed();
    }
}
