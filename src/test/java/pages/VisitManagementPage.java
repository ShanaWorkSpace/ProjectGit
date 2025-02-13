package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VisitManagementPage {
    WebDriver driver;

    public VisitManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    // Add a new visit
    public boolean addVisit(String institution, String category, String date, String remarks) {
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Add Visit')]")).click();

            driver.findElement(By.name("institution")).sendKeys(institution);
            driver.findElement(By.name("category")).sendKeys(category);
            driver.findElement(By.name("date")).sendKeys(date);
            driver.findElement(By.name("remarks")).sendKeys(remarks);

            driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
            
            // Verify if visit is added
            return driver.findElements(By.xpath("//td[contains(text(),'" + institution + "')]")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Update an existing visit
    public boolean updateVisit(String oldInstitution, String newDate, String newRemarks) {
        try {
            WebElement visitRow = driver.findElement(By.xpath("//td[contains(text(),'" + oldInstitution + "')]/.."));
            visitRow.findElement(By.xpath(".//button[contains(text(),'Edit')]")).click();

            WebElement dateField = driver.findElement(By.name("date"));
            dateField.clear();
            dateField.sendKeys(newDate);

            WebElement remarksField = driver.findElement(By.name("remarks"));
            remarksField.clear();
            remarksField.sendKeys(newRemarks);

            driver.findElement(By.xpath("//button[contains(text(),'Update')]")).click();
            
            return driver.findElement(By.xpath("//td[contains(text(),'" + newDate + "')]")) != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Delete a visit
    public boolean deleteVisit(String institution) {
        try {
            WebElement visitRow = driver.findElement(By.xpath("//td[contains(text(),'" + institution + "')]/.."));
            visitRow.findElement(By.xpath(".//button[contains(text(),'Delete')]")).click();

            driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
            
            Thread.sleep(2000);  // Wait for deletion to complete

            return driver.findElements(By.xpath("//td[contains(text(),'" + institution + "')]")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

	}
