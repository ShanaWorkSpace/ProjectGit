package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	    WebDriver driver;

	    // Constructor to initialize the WebDriver
	    public LoginPage(WebDriver drv) {
	        this.driver = drv;
	    }

	    // Method to input user_name
	    public void username(String username) {
	        WebElement uname = driver.findElement(By.xpath("//input[@name='userId']"));
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
		        viewButton.click();}
		        
	    // Method to click the login button
	    public void buttonclick() {
	        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
	        loginButton.click();
	    }
	    public String Dashcheck() {
	        // Verify login is successful by checking a specific element (dash_board title)
	        WebElement dashboardElement = driver.findElement(By.xpath("//p[contains(@class, 'text-') and contains(text(), 'Manager 1')]"));
	        return dashboardElement.getText();
	      
	     
	    }
	    public void Count()
	    {
	    	WebElement total_visit=driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[1]/div[2]/div/h2"));
	    	String total_visits = total_visit.getText();
	    	WebElement upcoming_visit=driver.findElement(By.xpath("/html/body/div/div/div/main/div[1]/div[2]/div[2]/div/h2"));
	    	String upcoming_visits = upcoming_visit.getText();
	    	WebElement visits = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/p[1]"));
	    	String visit = visits.getText();
	    		
	    }
}

