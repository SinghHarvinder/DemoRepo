package com.mavendemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage {

	@ FindBy( name="username")
	private WebElement unTxtBox;

	@ FindBy( name="pwd")
	private WebElement pwdTxtBox;

	@FindBy (id="loginButton")
	private WebElement loginBtn;
	
//	@ FindBy( css="input[value*='Login now']")
//	private WebElement loginBtn;

//    @ FindBy(how=How.XPATH,using="//span[contains(text(),'Username or Password is invalid. Please try again.')]")
//    private WebElement errormsg;
  
	@FindBy (xpath="//span[contains(text(),'Username or Password is invalid. Please try again.')]")
	private WebElement errormsg;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}


	public void login(String username ,String password)
	{
		unTxtBox.sendKeys(username);
		pwdTxtBox.sendKeys(password);
		loginBtn.click();
		
	}

	public void verifyErrorMsg()
	{
		String expMsg="Username or Password is invalid. Please try again.";
		String actMsg=errormsg.getText();
		Assert.assertEquals(expMsg, actMsg);
//Assert.assertEquals(errormsg.getText(), "Username or Password is invalid. Please try again.");	//Assert.assertTrue(errormsg.isDisplayed(), "Error Message is not displayed");
	    Reporter.log("Invalid login message is displayed",true);
	}
	

}
