package com.mavendemo.genericlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import static com.mavendemo.genericlib.MyProjectListeners.*;

public class BaseLib {
	public WebDriver wDriver;
	public EventFiringWebDriver driver;
	MyProjectListeners listeners;
	//WaitStatementLib wlib;
	
@BeforeMethod
@Parameters("browser")
public void setUp(String browserName )
//public void setUp()
{
//String browserName=GetPropertyValue.getValue("browser"); // we write this line when we not want to execute test script by testng.xml file 
	if(browserName.equalsIgnoreCase("firefox"))
	{
		wDriver=new FirefoxDriver();
		//driver =new RemoteWebDriver(DesiredCapabilities.firefox());
		Reporter.log("Firefox Browser is lanched",true);
	}
	
	else if (browserName.equalsIgnoreCase("ie"))
	{
		System.setProperty("webdriver.ie.driver",".\\exefiles\\IEDriverServer.exe");
		wDriver=new InternetExplorerDriver();
		//driver =new RemoteWebDriver(DesiredCapabilities.internetExplorer());
		Reporter.log("IE Browser is lanched",true);
		}
	
	else if(browserName.equalsIgnoreCase("chrome"))
	{
	wDriver=new ChromeDriver();
		//driver =new RemoteWebDriver(DesiredCapabilities.chrome());
	System.setProperty("webdriver.chrome.driver",".\\exefiles\\chromedriver.exe");
	Reporter.log("Chrome Browser is lanched",true);
	}
	listeners =new MyProjectListeners();
	driver=new EventFiringWebDriver(wDriver);
	driver.register(listeners);
	WaitStatementLib.implicitWaitForSeconds(20, driver);
	driver.get(GetPropertyValue.getValue("testURL"));
	Reporter.log("navigating to url ",true);
	
	}

	@AfterMethod
	public void tearDown()
	{
		
		if(driver!=null)
		{
			driver.close();
			Reporter.log("Browser closed", true);
		}
		
	}

	@AfterSuite
	public void configAfterSuite()
	{
Reporter.log("Running After Suite: ",true);
DateFormat dateFormat=new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
Date date=new Date();
String TimeStamp=dateFormat.format(date);
String XlPath ="./reports/excelreports/Report"+TimeStamp+".xlsx";
FileOutputStream fos;
try
{
	fos= new FileOutputStream(new File(XlPath));
	Workbook wb=new HSSFWorkbook();
	Sheet s=wb.createSheet("Result");
	ExcelUtils.writeData(s, 0, "Script Passed:", iPassCount);
	ExcelUtils.writeData(s, 1, "Script Failed:", iFailCount);
	ExcelUtils.writeData(s, 2, "Script Skipped:", iSkipCount);
	try
	{
		wb.write(fos);
		fos.close();
	}
	catch(IOException e)
	{
		Reporter.log("Unable to write messages to Excel" ,true);
		e.printStackTrace();
	}}
	catch(FileNotFoundException e)
	{
		Reporter.log("Excel File is not creating ",true);
	}
}
	

}