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
        // Using a 10-second explicit wait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Refresh and get the current URL
    public String getCurrentUrl() {
        driver.navigate().refresh();
        return driver.getCurrentUrl();
    }

    // Fetch Dashboard Title
    public String getDashboardTitle() {
        WebElement titleElement = driver.findElement(By.xpath("//h3[contains(text(),'Dashboard')]"));
        return titleElement.getText();
    }

    // Get User Role text (e.g., "Manager 1", "Associate 1", etc.)
    public String getUserRole() {
        WebElement roleElement = driver.findElement(By.xpath("//*[@id='root']/div/header/div[2]/div[2]/p"));
        return roleElement.getText().trim();
    }

    // Get Total Visits Count
    public String getTotalVisits() {
        By totalVisitsLocator = By.xpath("//h2[contains(@class, 'font-bold text-[5.2rem]')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalVisitsLocator));
        WebElement totalVisits = driver.findElement(totalVisitsLocator);
        return totalVisits.getText();
    }

    // Get Upcoming Visits Count
    public String getUpcomingVisits() {
        By locator = By.xpath("//div[contains(text(),'Upcoming Visits')]/following-sibling::h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // Get Organizational Statistics Count
    public String getOrganizationalStats() {
        By locator = By.xpath("//div[contains(text(),'Organizational Statistics')]/following-sibling::h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // Get Visit View Count
    public String getVisitView() {
        By locator = By.xpath("//div[contains(text(),'Visit View')]/following-sibling::h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // Open notification panel and return true if visible
    public boolean openNotification() {
        By iconLocator = By.cssSelector("button.notification-icon");
        By popupLocator = By.cssSelector("div.notification-popup");
        wait.until(ExpectedConditions.elementToBeClickable(iconLocator));
        driver.findElement(iconLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
        return driver.findElement(popupLocator).isDisplayed();
    }

    // Consolidated check: Verify that key Dashboard elements are displayed
    public boolean isDashboardDisplayed() {
        try {
            WebElement totalVisit = driver.findElement(By.xpath("//h2[text()='Total Visits']"));
            WebElement upcomingVisits = driver.findElement(By.xpath("//h2[text()='Upcoming Visits']"));
            WebElement visitView = driver.findElement(By.xpath("//h2[text()='Visit View']"));
            WebElement orgStats = driver.findElement(By.xpath("//h2[text()='Organization Statistics']"));
            return totalVisit.isDisplayed() && upcomingVisits.isDisplayed() &&
                   visitView.isDisplayed() && orgStats.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
