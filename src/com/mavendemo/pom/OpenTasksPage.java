package com.mavendemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

public class OpenTasksPage extends BasePage{
	@FindBy (xpath="//a[text()='Projects & Customers']")
	private WebElement projAndCustLink;
	
	@FindBy (css="input[value='Create New Tasks']")
	private WebElement createNewTaskLink;
	
	@FindBy  (name="customerId")
	private WebElement custDropdown;
	
	@FindBy  (name="projectId")
	private WebElement projDropdown;
	
	
	@FindBy ( name="task[0].name")
	private WebElement taskNameTextBx;
	
	@FindBy  (id="ext-gen7")
	private WebElement deadLineCalenderImg;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement calOkBtn;
	
	@FindBy (name="task[0].billingType")
	private WebElement billingTypeDropDwn;
	
	
//	@FindBy (xpath="//input[@name='task[0].name']/../following-sibling::td/div//option[text()='Billable']")
//	private WebElement billableOption;	
	
	@FindBy (css ="input[value='Create Tasks']")
	private WebElement createTasksBtn;
	
	@FindBy(xpath="//a[text()='eatapple']")
	private WebElement taskLink;
	
	@FindBy(className="successmsg")
	private WebElement successmsg;
	
	public OpenTasksPage(WebDriver driver)
	{
		super(driver);//n you  want  to intialize the webelement then use this constructor to get clickOnlogoutLink() and verifyHomePageLogo
		PageFactory.initElements(driver, this);
	}
	
	public void verifyHomePage(WebDriver driver)
	{
		String expTitle="actiTIME - Open Tasks";
	    String actTitle=driver.getTitle();
	    Assert.assertEquals(actTitle, expTitle);
	    Reporter.log("HomePage title is verified" ,true);
	    
	    String expUrl="http://127.0.0.1:89/tasks/otasklist.do";
	    String actUrl=driver.getCurrentUrl();
	    Assert.assertEquals(actUrl, expUrl);
	    Reporter.log("HomePage Url is verified" ,true);
	       
	    
	}
   public void clickOnProjAndCustLink()
   {
	projAndCustLink.click();   
   }
   
   public void createTask(String TaskName)
   {
	   createNewTaskLink.click();
	   Select sc =new Select(custDropdown);
       sc.selectByVisibleText("monster");
       Select scp=new Select(projDropdown);
       scp.selectByVisibleText("apple");
	   taskNameTextBx.sendKeys(TaskName);
	   deadLineCalenderImg.click();
	   calOkBtn.click();
	   Select scb=new Select(billingTypeDropDwn);
	   scb.selectByValue("1");
	   createTasksBtn.click();
	   Reporter.log("Task is created successfully",true);
	   }
   
   public void clickOnTaskLink()
   {
	   taskLink.click();
   }
   
   public void verifyDeleteTaskMsg()
   {
	   String expMsg="Task has been successfully deleted.";
		 String actMsg=successmsg.getText();
		 Assert.assertEquals(expMsg, actMsg);
		 Reporter.log("Delete Task message is verified",true);
	   
	   
   }
}
