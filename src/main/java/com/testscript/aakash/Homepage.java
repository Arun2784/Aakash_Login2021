package com.testscript.aakash;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Homepage extends Homepage_element
{	
	String random_user=RandomNumber();
	
	@Test(priority = 1, description="Homepage>>Test No.1>>Verify User navigation to SIT Homepage")
	public void homepage() throws InterruptedException
	{
		test=extent.createTest("Verify User navigation to SIT Homepage");
		String error=null;
		try
		{
		error=homepageico;
		//Verifying Home page Icon is displayed
		Assert.assertTrue(driver.findElement(By.xpath(homepageico)).isDisplayed());
		//Verifying all results reserved
		Assert.assertTrue(driver.findElement(By.xpath(footermsg)).isDisplayed());
	}
		catch(AssertionError e)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel(error + "  is not displayed", ExtentColor.RED));
		}
	}
	
	@Test(priority = 2, description="Homepage>>Test No.2>>Verify User is able Register Successfully")
	public void reg_user()
	{
		test=extent.createTest("Verify User is able Register Successfully");
		//Clicking on Sign Up link
		driver.findElement(By.xpath(signlink)).click();
		//Entering First name and entering name as Random
		driver.findElement(By.xpath(namefld)).sendKeys("Random User");
		//Entering Mobile and entering randomly generated mobile number
		driver.findElement(By.xpath(mobfld)).sendKeys(random_user);
		//Entering Email address and adding random number embedded in email address
		driver.findElement(By.xpath(emailfld)).sendKeys("user@"+random_user+".com");
		//Entering OTP 
		driver.findElement(By.xpath(otpfld)).sendKeys("0000");
		//Entering password
		driver.findElement(By.xpath(passwdfld)).sendKeys(password);
		//Entering Confirm password
		driver.findElement(By.xpath(passwdfld1)).sendKeys(password);
		//Clicking on Sign Up button
		driver.findElement(By.xpath(signbtn)).click();
		//Verifying User is logged in now
		wait(By.xpath(greeting));
		Assert.assertTrue(driver.findElement(By.xpath(greeting)).isDisplayed());
		//Clicking on Logout button
		driver.findElement(By.xpath(logoutlnk)).click();
		//Verifying all results reserved
		Assert.assertTrue(driver.findElement(By.xpath(footermsg)).isDisplayed());
	}
	
	
	@Test(priority = 3, description="Homepage>>Test No.3>>Verify User is able Login Successfully again")
	public void login_user() throws InterruptedException
	{
		test=extent.createTest("Verify User is able Login Successfully again");
	    String error = null;
	    //Sticky Header Links
		try  {
		// Clicking on Login link
		error=loginIco;
		Assert.assertTrue(driver.findElement(By.xpath(loginIco)).isDisplayed());
		driver.findElement(By.xpath(loginIco)).click();
		// Entering mobile number 
		error=name;
		Assert.assertTrue(driver.findElement(By.xpath(name)).isDisplayed());
		// Entering mobile number
		error=random_user;
		driver.findElement(By.xpath(name)).sendKeys(random_user);
		// Entering Password
		error=passw;
		Assert.assertTrue(driver.findElement(By.xpath(passw)).isDisplayed());
		driver.findElement(By.xpath(passw)).sendKeys(passd);
		//Clicking on Login button
		driver.findElement(By.xpath(signBtn)).click();
		//Verifying User is successfully Signed IN
		wait(By.xpath(greeting));
		Assert.assertTrue(driver.findElement(By.xpath(greeting)).isDisplayed());
		//Clicking on Logout button
		driver.findElement(By.xpath(logoutlnk)).click();
		//Verifying all results reserved
		Assert.assertTrue(driver.findElement(By.xpath(footermsg)).isDisplayed());
		} 
		catch(AssertionError e)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel(error + "  is not displayed", ExtentColor.RED));
		}
	}
	
	@Test(priority = 4, description="Homepage>>Test No.4>>Verify that Banner is displayed on Homepage")
	public void banner() throws InterruptedException
{
		test=extent.createTest("Verify that Banner is displayed on Homepage");
		//Adding a false condition so that the TC fails and screenshot is taken
		try {
		Assert.assertTrue(driver.findElement(By.xpath("THis is incorrect banner xpath")).isDisplayed());
		}
		catch(AssertionError e)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel("Banner is not displayed", ExtentColor.RED));
		}
}
}