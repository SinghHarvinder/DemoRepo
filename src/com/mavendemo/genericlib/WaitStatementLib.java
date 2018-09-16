package com.mavendemo.genericlib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitStatementLib {
	 //WebDriverWait wait ;
	 
	 public static void isleep(int sec )
	 {
		 try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	public  static void implicitWaitForSeconds(int time ,WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		
	}
	
	public  static void implicitWaitForMinutes(int time ,WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.MINUTES);
		
	}
	 public static void explicitWaitForClickable(int time , WebElement ele , WebDriver driver)
	 {
		 WebDriverWait wait= new WebDriverWait(driver,time);
		 
		 wait.until(ExpectedConditions.elementToBeClickable(ele));
	 }
	 
	 public static void explicitWaitForVisibility(int time , WebElement ele , WebDriver driver)
	 {
		 WebDriverWait wait= new WebDriverWait(driver,time);
		 
		 wait.until(ExpectedConditions.visibilityOf(ele));
	 }

}
