package com.mavendemo.scripts;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.mavendemo.genericlib.BaseLib;
import com.mavendemo.genericlib.ExcelUtils;
import com.mavendemo.genericlib.WaitStatementLib;
import com.mavendemo.pom.LoginPage;
import com.mavendemo.pom.OpenTasksPage;



public class LoginTest extends BaseLib{
	String sTestCaseID=null;
	String[] sData=null;
	
	LoginPage lp;
	WaitStatementLib wLib;

	@Test 
	public void validLoginTest()
	{
		sTestCaseID="Login_ID";
		wLib=new WaitStatementLib();
		sData =ExcelUtils.toReadExcelData(sTestCaseID);
		lp=new LoginPage(driver);
//		String username = ExcelUtils.readData("Sheet1",1, 1);
//		String password = ExcelUtils.readData("Sheet1",1, 2);
		System.out.println("validLogin: "+sData[1]+","+sData[2]);
		lp.login(sData[1], sData[2]);
		WaitStatementLib.isleep(2);
		Reporter.log("login successfully", true);
		OpenTasksPage otp= new OpenTasksPage(driver);
	     otp.verifyHomePage(driver);
	     otp.verifyHomepageLogo();
		//add one line for git testing purpose
	}

	     @Test
	 	public void invalidLoginTest()
	 	{
	    	 sTestCaseID="InvalidLogin_ID";
	    	 sData =ExcelUtils.toReadExcelData(sTestCaseID);
	 		wLib=new WaitStatementLib();
	 		lp=new LoginPage(driver);
	 		System.out.println("inValidLogin: "+sData[1]+","+sData[2]);
//	 		String username = ExcelUtils.readData("Sheet1",2, 1);
//	 		String password = ExcelUtils.readData("Sheet1",2, 2);
	 		lp.login(sData[1],sData[2]);
	 		try{
	 			Thread.sleep(2000);
	 		}
	 		catch(InterruptedException e){
	 			e.printStackTrace();
	 		}
	 		Reporter.log("login Unsuccessfully", true);
	 		lp.verifyErrorMsg();
	 	    
	 }
}
