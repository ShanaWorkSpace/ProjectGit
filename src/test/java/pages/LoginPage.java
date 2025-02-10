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
		    public String Dashcheck() {
		        WebElement dashboardElement = driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div[2]/p"));
		        return dashboardElement.getText();
		    }
		    
		    public String getErrorMessage() {
		        WebElement errorElement = driver.findElement(By.xpath("//div[@class='error-message']"));
		        return errorElement.getText();
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

