package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UpcomingVisit {
    WebDriver driver;
    
    public UpcomingVisit(WebDriver drv) {
        this.driver = drv;
    }
    
    // Verify that the Upcoming Visits section and its table headers are displayed
    public boolean isdisplayed() {
        try {
            WebElement fieldTitle = driver.findElement(By.xpath("(//h2[text()='Upcoming Visits'])[2]"));
            WebElement inst = driver.findElement(By.xpath("//th[text()='Institutions']"));
            WebElement instType = driver.findElement(By.xpath("//th[text()='Institutions Type']"));
            WebElement date = driver.findElement(By.xpath("//th[text()='Date']"));
            WebElement empName = driver.findElement(By.xpath("//th[text()='Employee Name']"));
            WebElement cntPerson = driver.findElement(By.xpath("//th[text()='Contact Person']"));
            WebElement status = driver.findElement(By.xpath("//th[text()='Status']"));
            WebElement action = driver.findElement(By.xpath("//th[text()='Action']"));

            return fieldTitle.isDisplayed() && inst.isDisplayed() &&
                   instType.isDisplayed() && date.isDisplayed() &&
                   empName.isDisplayed() && cntPerson.isDisplayed() &&
                   status.isDisplayed() && action.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Click on the view button for a particular upcoming visit.
    // Wait until any overlay (e.g. modal with id "static-modal") disappears and then click.
    public void viewData() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait until the modal overlay is gone
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("static-modal")));
        WebElement view = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='text-blue-700 flex items-center space-x-1'])[4]")));
        view.click();
    }
    
    // After clicking view, verifies that the Visit Details popup is displayed
    public boolean viewdetailsDisplay() {
        viewData();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement details = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Visit Details']")));
            WebElement purpose = driver.findElement(By.xpath("//h3[text()='Visit Purpose']"));
            return details.isDisplayed() && purpose.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Closes the view details popup
    public void viewdataClose() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Close'])[2]")));
        close.click();
    }
    
    // Clicks on the update action button
    public void actionUpdate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait until any interfering overlay disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("static-modal")));
        WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='text-blue-700 flex items-center space-x-1'])[6]")));
        updateBtn.click();
    }
    
    // After clicking update, verifies that the update form is displayed
    public boolean updateformDisplay() {
        // Optionally wait for the update form header to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Update visit']")));
            return form.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Performs the update action by selecting a status and submitting the form
    public void update() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement updateStatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='Action Needed'])[2]")));
        updateStatus.click();
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Submit'])[1]")));
        submit.click();
    }
    
    // Verifies that the update success popup is displayed
    public boolean updateSuccess() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Updated Successfully']")));
            WebElement closebtn = driver.findElement(By.xpath("(//button[text()='Close'])[1]"));
            return popup.isDisplayed() && closebtn.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Closes the update success popup
    public void updateClose() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Close'])[1]")));
        closebtn.click();
    }
}
