package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddVisitPage {
    WebDriver driver;
    
    public AddVisitPage(WebDriver drv) {
        this.driver = drv;
    }
    
    // Check if the "Add New Visit" header is displayed
    public boolean addvisit_displayed() {
        try {
            WebElement addvisit = driver.findElement(By.xpath("//h1[text()='Add New Visit']"));
            return addvisit.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Click on the "Add New Visit" header (or button) to open the visit form
    public void add_visit() {
        WebElement clicknew = driver.findElement(By.xpath("//h1[text()='Add New Visit']"));
        clicknew.click();
    }
    
    // Click on the "Add" button to add an institution (if needed)
    public void add_inst() {
        WebElement addBtn = driver.findElement(By.xpath("(//button[text()='Add'])[3]"));
        addBtn.click();
    }
    
    // Select the institution from a dropdown
    public void select_inst() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inst = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=' css-19bb58m'])[5]")));
        inst.click();
        WebElement select = driver.findElement(By.xpath("//div[contains(text(), 'Brennen college Thalassery')]"));
        select.click();
    }
    
    // Select an employee from a dropdown. Pass the employee name as parameter.
    public void select_emp(String empName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=' css-19bb58m'])[6]")));
        emp.click();
        WebElement selectEmp = driver.findElement(By.xpath("//div[text()='" + empName + "']"));
        selectEmp.click();
    }
    
    // Set the visit date and time. Expected format: yyyy-MM-ddThh:mm
    public void date_time() {
        WebElement date = driver.findElement(By.xpath("(//input[@id='visitDateTime'])[2]"));
        date.click();
        date.clear();
        date.sendKeys("2025-02-15T10:30");
    }
    
    // Enter the purpose of the visit
    public void purpose() {
        WebElement purpose = driver.findElement(By.xpath("(//input[@id='purpose'])[2]"));
        purpose.sendKeys("Meeting");
    }
    
    // Select the contact person from a dropdown
    public void contactPerson() {
        WebElement contact = driver.findElement(By.xpath("(//div[@class=' css-19bb58m'])[7]"));
        contact.click();
        WebElement person = driver.findElement(By.xpath("//div[text()='test (tester)']"));
        person.click();
    }
    
    // Add additional notes to the visit
    public void addNote() {
        WebElement note = driver.findElement(By.xpath("(//input[@id='additionalNotes'])[2]"));
        note.sendKeys("NA");
    }
    
    // Enter the discussion topic
    public void dis_topic() {
        WebElement topic = driver.findElement(By.xpath("(//input[@id='discussionTopic'])[2]"));
        topic.sendKeys("Project Proposal");
    }
    
    // Upload a file via the file input element.
    // This method attempts to locate the file input using one locator.
    // (For Associate you might need to use a different locator.)
    public void upload_file(String filePath) {
        try {
            // First try the input element locator (commonly used by Manager)
            WebElement uploadButton = driver.findElement(By.xpath("//input[@type='file']"));
            uploadButton.sendKeys(filePath);
        } catch(Exception e) {
            // Fallback: try using the label locator (as used by Associate)
            WebElement uploadLabel = driver.findElement(By.xpath("(//label[text()='Browse'])[2]"));
            uploadLabel.sendKeys(filePath);
        }
    }
    
    // Cancel the visit creation
    public void cancel_visit() {
        WebElement cancel = driver.findElement(By.xpath("(//button[text()='Cancel'])[3]"));
        cancel.click();
    }
    
    // (Optional) Submit the visit
    public void submit_visit() {
        WebElement submit = driver.findElement(By.xpath("(//button[text()='Submit'])[2]"));
        submit.click();
    }
    
    // Check if a success pop-up is displayed after submission
    public boolean isPopupDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Yeah , you have added a visit']")));
            return popup.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Close the success pop-up
    public void popup_close() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement closebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Close'])[2]")));
            closebtn.click();
            System.out.println("✅ Pop-up closed successfully.");
        } catch (Exception e) {
            System.out.println("❌ Failed to close pop-up: " + e.getMessage());
        }
    }
}
