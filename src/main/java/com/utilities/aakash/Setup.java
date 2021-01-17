package com.utilities.aakash;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Setup  {

	public static WebDriver driver;
	
	
	
	public void Start()
	{
		//System.setProperty("webdriver.chrome.driver", "E:\\Selenium Training//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "./Inputs/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("http://digital-sit.aakash.ac.in/");
		
		
		
	}

	public void Mouseover(By by)
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(by)).perform();
		
	}
	public void Stop()
	{
		driver.close();
	}
	
	public void wait(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, 240);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));			
	}
	
	public void clickablewait(By by)
	{
		WebDriverWait clickablewait = new WebDriverWait(driver, 240);
		clickablewait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public  static String RandomNumber()
	{

		int RanNumber = 0; 
		RanNumber = (int)((Math.random() * 90000000)+100000000); 
		return("9"+String.valueOf(RanNumber));
	}
	
//Practice
	//driver.manage.timeout.implicitwait(Timeout, TimeUnit.secon)--Implicit wait
	
//WebDriverWait wait = new WebDriverWait(driver, 20);
//	WebElement ele = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("")));--Explicit wait
}

	


