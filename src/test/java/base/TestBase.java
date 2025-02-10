package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
	protected WebDriver driver;
	@BeforeTest
	
	public void setUp() {
	driver = new ChromeDriver();
    // Open the OrangeHRM login page
    driver.get("http://dev.visit.ictkerala.org/");
    // Maximize the browser window
    driver.manage().window().maximize();
    // Set implicit wait for element loading
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
}
	@AfterTest
	public void aftertest()
	{
		driver.quit();
	}
	}