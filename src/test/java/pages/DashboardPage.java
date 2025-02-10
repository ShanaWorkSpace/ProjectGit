package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get current URL
    public String getCurrentUrl() {
    	driver.navigate().refresh();    
        return driver.getCurrentUrl();
    }

    // Fetch Dashboard Title
    public String getDashboardTitle() {
        WebElement titleElement = driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]"));
        return titleElement.getText();
    }

    // Get User Role (e.g., "Manager 1", "Associate 1", "Associate 2")
    public String getUserRole() {
        WebElement roleElement = driver.findElement(By.xpath("//p[contains(@class, 'text-')]"));
        return roleElement.getText().trim();
    }

    // Get Total Visits Count
    public String getTotalVisits() {
        WebElement totalVisits = driver.findElement(By.xpath("//div[contains(text(),'Total Visits')]/following-sibling::h2"));
        return totalVisits.getText();
    }

    // Get Upcoming Visits Count
    public String getUpcomingVisits() {
        WebElement upcomingVisits = driver.findElement(By.xpath("//div[contains(text(),'Upcoming Visits')]/following-sibling::h2"));
        return upcomingVisits.getText();
    }

    // Get Organizational Statistics Count
    public String getOrganizationalStats() {
        WebElement orgStats = driver.findElement(By.xpath("//div[contains(text(),'Organizational Statistics')]/following-sibling::h2"));
        return orgStats.getText();
    }

    // Get Visit View Count
    public String getVisitView() {
        WebElement visitView = driver.findElement(By.xpath("//div[contains(text(),'Visit View')]/following-sibling::h2"));
        return visitView.getText();
    }

    // Open notification panel
    public boolean openNotification() {
        WebElement notificationIcon = driver.findElement(By.cssSelector("button.notification-icon"));
        notificationIcon.click();
        WebElement notificationPopup = driver.findElement(By.cssSelector("div.notification-popup"));
        return notificationPopup.isDisplayed();
    }

    // Close notification
    public boolean closeNotification() {
        WebElement closeBtn = driver.findElement(By.cssSelector("button.close-notification"));
        closeBtn.click();
        return driver.findElements(By.cssSelector("div.notification-popup")).isEmpty();
    }
}
