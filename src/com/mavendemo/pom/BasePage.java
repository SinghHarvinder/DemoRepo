package com.mavendemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class BasePage {

	@FindBy(xpath="//img[contains(@src,'default-logo.png')]")
	private WebElement logo;
	
	@FindBy(id="logoutLink")
	private WebElement logoutLink;
	
	@FindBy(xpath="//div[text()='Users']")
	private WebElement userLink;
	
	public  BasePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void verifyHomepageLogo()
	{
		Assert.assertTrue(logo.isDisplayed());
		Reporter.log("HomePage logo is displayed", true);
	}
	
	public void clickOnLogoutLink()
	   {
		   logoutLink.click();
	   }

	public void clickOnUserLink()
	{
		userLink.click();
	}
}
