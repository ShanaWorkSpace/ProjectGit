package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    WebDriver driver;
	private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second explicit wait
    }

    // Get current URL
    public String getCurrentUrl() {
    	driver.navigate().refresh();    
        return driver.getCurrentUrl();
    }

    // Fetch Dashboard Title
    public String getDashboardTitle() {
        WebElement titleElement = driver.findElement(By.xpath("//h3[contains(text(),'Dashboard')]"));
        return titleElement.getText();
    }

    // Get User Role (e.g., "Manager 1", "Associate 1", "Associate 2")
    public String getUserRole() {
        WebElement roleElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div[2]/div[2]/p"));
        return roleElement.getText().trim();
    }

    // Get Total Visits Count
    public String getTotalVisits() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By totalVisitsLocator = By.xpath("//h2[contains(@class, 'font-bold text-[5.2rem]')]");
        
        // Wait until the element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalVisitsLocator));

        WebElement totalVisits = driver.findElement(totalVisitsLocator);
        return totalVisits.getText();
    }

 // Get Upcoming Visits Count with Wait
    public String getUpcomingVisits() {
        By locator = By.xpath("//div[contains(text(),'Upcoming Visits')]/following-sibling::h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // Get Organizational Statistics Count with Wait
    public String getOrganizationalStats() {
        By locator = By.xpath("//div[contains(text(),'Organizational Statistics')]/following-sibling::h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // Get Visit View Count with Wait
    public String getVisitView() {
        By locator = By.xpath("//div[contains(text(),'Visit View')]/following-sibling::h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // Open notification panel with Wait
    public boolean openNotification() {
        By iconLocator = By.cssSelector("button.notification-icon");
        By popupLocator = By.cssSelector("div.notification-popup");

        // Wait for the notification icon to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(iconLocator));
        driver.findElement(iconLocator).click();

        // Wait for the notification popup to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
        return driver.findElement(popupLocator).isDisplayed();
    }
}
