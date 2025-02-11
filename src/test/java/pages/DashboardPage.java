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
        WebElement titleElement = driver.findElement(By.xpath("/html/body/div/div/header/div[1]/div/h3]"));
        return titleElement.getText();
    }

    // Get User Role (e.g., "Manager 1", "Associate 1", "Associate 2")
    public String getUserRole() {
        WebElement roleElement = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[2]/p"));
        return roleElement.getText().trim();
    }

    // Get Total Visits Count
    public String getTotalVisits() {
        WebElement totalVisits = driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[1]/div[2]/div/h2"));
        return totalVisits.getText();
    }

    // Get Upcoming Visits Count
    public String getUpcomingVisits() {
        WebElement upcomingVisits = driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[2]/div[2]/div/h2"));
        return upcomingVisits.getText();
    }

    

    // Open notification panel
    public boolean openNotification() {
        WebElement notificationIcon = driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[5]/div/div/div[1]/div/div/div[2]/h1"));
        notificationIcon.click();
        WebElement notificationPopup = driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[5]/div/div/div[2]/div/div/div[2]/div[1]/div/div[2]/span"));
        return notificationPopup.isDisplayed();
    }

    // Close notification
    public boolean closeNotification() {
        WebElement closeBtn = driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[5]/div/div/div[2]/div/div/div[2]/div[1]/div/div[3]/svg"));
        closeBtn.click();
        return driver.findElements(By.xpath("/html/body/div/div/div/main/div[1]/div[5]/div/div/div[1]/div/div/div[2]/p")).isEmpty();
    }
}
