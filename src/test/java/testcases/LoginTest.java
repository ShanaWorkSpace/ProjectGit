package testcases;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;

public class LoginTest extends TestBase {
	LoginPage Obj;
	@BeforeClass
	public void objInt()
	{
		Obj=new LoginPage(driver);
	}
	@Test
	public void firsttest() 
	{
		Obj.username();
		Obj.password();
		Obj.view_buttonclick();
		Obj.buttonclick();
		String user=Obj.Dashcheck();
		Assert.assertEquals(user, "Manager 1");
		
		}

}