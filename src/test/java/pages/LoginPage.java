package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	    WebDriver driver;
		private WebDriverWait wait;

	    // Constructor to initialize the WebDriver
	    public LoginPage(WebDriver drv) {
	        this.driver = drv;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 5-second explicit wait
	        
	    }

	    // Method to input user_name
	    public void username(String username) {
	        WebElement uname = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div[2]/div[1]/div/input"));
	        uname.clear();
	        uname.sendKeys(username);
	    }

	    // Method to input password
	    public void password(String password) {
	        WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
	        pwd.clear();
	        pwd.sendKeys(password);
	    }
	     	     
	  // Method to click the login button
		    public void view_buttonclick() {
		        WebElement viewButton = driver.findElement(By.cssSelector("svg.h-6.text-gray-700.block"));
		        viewButton.click();
		        
		        WebElement LoginButton = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div[2]/div[4]/button"));
		        LoginButton.click();
		        
		    }
		        
	    // Method to click the login button
		    public void outprofclick() {
	            WebElement logoutprofile = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[1]/div/img"));	            
	            logoutprofile.click();}
		    
	        public void outbuttonclick() {
	            WebElement logoutButton = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[1]/div[2]/div[2]"));
	            logoutButton.click();
	            }
	        
	        
	    
	        // Method to check the dashboard name
	        public String Dashcheck() {
	            // List of possible XPaths (relative paths)
	            String[] xpaths = {
	                "//header/div[2]/div[2]/p",  // Dashboard element 1
	                "//div[1]/div[2]/div[2]/div[1]/p[2]", // Dashboard element 2
	                "//div[1]/div[2]/div[2]/div[2]/p[2]", // Dashboard element 3
	                
	            };

	            for (String xpath : xpaths) {
	                try {
	                    // Wait for the element to be present
	                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	                    return element.getText(); // Return text if found
	                } catch (Exception e) {
	                    // Continue to the next element if not found
	                }
	            }
	            return "No matching element found!";
	        }
	        
	        
	    

	     
//	    public void Count()
//	    {
//	    	WebElement total_visit=driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[1]/div[2]/div/h2"));
//	    	String total_visits = total_visit.getText();
//	    	WebElement upcoming_visit=driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[2]/div[2]/div/h2"));
//	    	String upcoming_visits = upcoming_visit.getText();
//	    	WebElement visits = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/p[1]"));
//	    	String visit = visits.getText();
//	    		
//	    }

public String locateErrorMessage() {
    String[] xpaths = {
        "//div/div[1]/div[2]",
        "//div[1]/div[2]/div[2]/div[1]/p[2]",
        "//div[1]/div[2]/div[2]/div[2]/p[2]"
    };

    for (String xpath : xpaths) {
        try {
            WebElement errorElement = driver.findElement(By.xpath(xpath));
            return errorElement.getText();
        } catch (Exception ignored) {
        }
    }
    return "No error message found!";
}}
