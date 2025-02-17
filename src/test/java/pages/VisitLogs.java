package pages;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VisitLogs {
    WebDriver driver;
    // Default download folder path
    String downloadPath = System.getProperty("user.home") + "/Downloads"; 

    public VisitLogs(WebDriver drv) {
        this.driver = drv;
    }
            
    // Click the Visit Logs menu item
    public void click_logs() {
        WebElement logs = driver.findElement(By.xpath("//span[text()='Visit logs']"));
        logs.click();
    }
    
    // Verify that key elements of the Visit Logs page are displayed
    public boolean isDisplayed() {
        try {
            WebElement title = driver.findElement(By.xpath("//h3[text()='Visit Logs']"));
            WebElement visit = driver.findElement(By.xpath("//p[text()='Visits']"));
            WebElement cmplt = driver.findElement(By.xpath("//p[text()='Completed']"));
            WebElement pndng = driver.findElement(By.xpath("//p[text()='Pending']"));
            WebElement action = driver.findElement(By.xpath("//p[text()='Action']"));
            WebElement btncsv = driver.findElement(By.xpath("//a[text()='Export CSV']"));
            WebElement addinst = driver.findElement(By.xpath("//button[text()='Add Institutions']"));
            WebElement addvisit = driver.findElement(By.xpath("//button[text()='Add Visit']"));
            
            return title.isDisplayed() && visit.isEnabled() &&
                   cmplt.isDisplayed() && pndng.isDisplayed() &&
                   action.isDisplayed() && btncsv.isDisplayed() &&
                   addinst.isDisplayed() && addvisit.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Click the Export CSV button
    public void clickExportCSV() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btncsv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Export CSV']")));
        btncsv.click();
    }
    
    // Verify that a CSV file is present in the download folder
    public boolean isCSVDownloaded() {
        File folder = new File(downloadPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().endsWith(".csv")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Click the Add Visit button and select an institution from the dropdown
    public void add_visit() {
        WebElement clicknew = driver.findElement(By.xpath("//button[text()='Add Visit']"));
        clicknew.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inst = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=' css-19bb58m'])[6]")));
        inst.click();
        WebElement select = driver.findElement(By.xpath("//div[text()='Brennen college Thalassery [Kannur]']"));
	        select.click();
	    }
	}



