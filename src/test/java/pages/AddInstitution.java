package pages;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddInstitution {
    WebDriver driver;
    // Optionally, if you need VisitLogs functionality you can instantiate it here:
    // VisitLogs visit;
    
    public AddInstitution(WebDriver drv) {
        this.driver = drv;
    }
    
    // Open the "Add Institutions" form from the Visit Logs page
    public void btnAddInst() {
        // Assuming VisitLogs has already been used to navigate to Visit Logs:
        WebElement btnVisit = driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[5]/button/div/div[2]/h1"));
        btnVisit.click();
    }
    
    // Click on the institution type dropdown
    public void inst_type() {
        WebElement type = driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[9]"));
        type.click();
    }
    
    // Select the institution type based on role
    public void select_type(String role) {
        String xpath;
        if (role.equalsIgnoreCase("manager")) {
            xpath = "(//div[text()='Academic Institutions'])[10]";
        } else {
            xpath = "(//div[text()='Academic Institutions'])[6]";
        }
        WebElement select = driver.findElement(By.xpath(xpath));
        select.click();
    }
    
    // Enter the institution name with a random suffix
    public void inst_name() {
        String randomText = getRandomString(5); // Generate a 5-letter random string
        WebElement name = driver.findElement(By.xpath("//input[@id='organizationName']"));
        name.sendKeys("Testing Institute " + randomText);
    }
    
    // Enter a unique email for the institution
    public void inst_mail() {
        String randomText = getRandomString(5).toLowerCase(); // Ensure lowercase for email
        WebElement mail = driver.findElement(By.xpath("//input[@id='email' and @placeholder='Institution Email']"));
        mail.sendKeys("testinstitutes" + randomText + "@gmail.com");
    }
    
    // Helper method to generate a random alphabetic string
    public String getRandomString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
    
    // Enter the institution place
    public void inst_place() {
        WebElement place = driver.findElement(By.xpath("//input[@id='place' and @placeholder='Place']"));
        place.sendKeys("Trivandrum");
    }
    
    // Select the state from the dropdown
    public void inst_state() {
        WebElement state = driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[10]"));
        state.click();
        WebElement selectState = driver.findElement(By.xpath("//div[text()='Kerala']"));
        selectState.click();
    }
    
    // Select the district from the dropdown
    public void inst_dist() {
        WebElement district = driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[11]"));
        district.click();
        WebElement selectDist = driver.findElement(By.xpath("//div[text()='Thiruvananthapuram']"));
        selectDist.click();
    }
    
    // Click the Submit button to add the institution
    public void inst_submit() {
        WebElement submit = driver.findElement(By.xpath("(//button[text()='Submit'])[4]"));
        submit.click();
    }
    
    // Click the Cancel button to abort adding an institution
    public void inst_cancel() {
        WebElement cancel = driver.findElement(By.xpath("(//button[@type='button' and text()='Cancel'])[3]"));
        cancel.click();
    }
    
    // Verify that the success pop-up is displayed
    public boolean isDisplayed() {
        try {
            WebElement success = driver.findElement(By.xpath("//h3[text()='Thanks for adding new Institution ']"));
            return success.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Close the success pop-up
    public void popupclose() {
        WebElement close = driver.findElement(By.xpath("(//button[text()='Close'])[4]"));
        close.click();
    }
}
