package com.mavendemo.genericlib;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyProjectListeners implements ITestListener,WebDriverEventListener
{
	public static int iPassCount=0;
	public static int iFailCount=0;
	public static int iSkipCount=0;
	Logger  actiLog;
	long startTime;
	long stopTime;

	public MyProjectListeners()
	{
		DateFormat df=new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
		Date date=new Date();
		System.setProperty("LongTimeDate",df.format(date)); // here LongTimeDate is present in log4j.properties inside log file location 
		actiLog = Logger.getLogger(this.getClass());
		//or
		//actiLog = Logger.getLogger(MyProjectListeners.class);
	}
//ITestListener
	@Override
	public void onFinish(ITestContext arg0) {
		stopTime=System.currentTimeMillis();
		actiLog.info("Test Suite Execution finished: "+ new Timestamp(new Date().getTime())); // it give time when your test execution ended
		actiLog.info("Total time for test suite execution: "+ (double) (stopTime-startTime)/1000+"seconds"); //it convert millisecond into seconds and downcast in double because after divide by 1000 it give time in points 
	}
//ITestListener
	@Override
	public void onStart(ITestContext arg0) {
		 startTime=System.currentTimeMillis();
	PropertyConfigurator.configure("log4j.properties");
	actiLog.info("Test Suite Execution started: "+ new Timestamp(new Date().getTime())); // it give when your test execution start
	}
//ITestListener
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
//ITestListener
	@Override
	public void onTestFailure(ITestResult result) {
		iFailCount++;
		System.out.println("Test Failure: "+iFailCount);
		actiLog.info(result.getName()+" script has been failed "); // >> give test method name 
		BaseLib obj=(BaseLib) result.getInstance(); //
		DateFormat dateFormat=new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
		Date date=new Date();
		
			String fileName=result.getMethod().getMethodName().replace("Test", "");
			TakesScreenshot screenShots= (TakesScreenshot) obj.driver;
			File srcFile=screenShots.getScreenshotAs(OutputType.FILE);
		File destFile=new File(".\\screenshots\\"+fileName+dateFormat.format(date)+".png");
	try
			{
		FileUtils.copyFile(srcFile, destFile);
	}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
//ITestListener
	@Override
	public void onTestSkipped(ITestResult result) {
		iSkipCount++;
		System.out.println("Test Skipped: "+iSkipCount);
		actiLog.info(result.getName()+" script has been skipped ");
		
	}
//ITestListener
	@Override
	public void onTestStart(ITestResult result) {
		actiLog.info(result.getName()+" script execution has been started: "+new Timestamp(new Date().getTime()));
		
		
	}
//ITestListener
	@Override
	public void onTestSuccess(ITestResult result) {
		iPassCount++;
		System.out.println("Test Pass: "+iPassCount);
		actiLog.info("TestScript Name: "+result.getName()+" /Status--->pass :)");		
		
		
	}
	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterClickOn(WebElement ele, WebDriver driver) {
		
	System.out.println("After Click on: "+driver.getTitle()+driver.getCurrentUrl());
	System.out.println("Element Clicked :"+ele.toString());
	//System.out.println("Element Clicked :"+ele.getTagName());
	}
	@Override
	public void afterFindBy(By by, WebElement ele, WebDriver driver) {
		System.out.println("Element found :"+by);
		
	}
	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
	
		System.out.println("after navigate to the :"+url); 
		System.out.println("Current url is :"+driver.getCurrentUrl()); //>> url that is opened earlier
	}
	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeClickOn(WebElement ele, WebDriver driver) {
		System.out.println("Befpore click on the title of the page: "+driver.getTitle());
		System.out.println("Before click on the url of the page: "+driver.getCurrentUrl());
		System.out.println("Trying to click :"+ele.getTagName());
		
	}
	@Override
	public void beforeFindBy(By by, WebElement ele, WebDriver driver) {
		System.out.println("Trying to find :"+by);
		
	}
	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Befor navigate To :"+url+", my url was :"+driver.getCurrentUrl());
		
	}
	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onException(Throwable t, WebDriver arg1) {
		actiLog.fatal("Execution interrupted....:(");
        actiLog.fatal(t.getMessage());		
	}
}
