package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

//import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage{
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "lastname")
	   private WebElement lastnameEdit;

	   @FindBy(xpath="//img[@alt='Create Contact...']")
		private WebElement createNewcontactBtn;
	   
	   @FindBy(name="support_start_date")
	   private WebElement supportStartDateEdit;
	   
	   @FindBy(name="support_end_date")
	  private WebElement supportendDateEdit;
	   
	   @FindBy(xpath="//img[@alt='Select']")
	   private WebElement lookupImgBtn;
	   
	   @FindBy(name="search_text")
	   private WebElement searchIndiffpageEdit;
	   
	   @FindBy(name="search")
	   private WebElement searchNowBtn;
	   
	   @FindBy(xpath="//input[@title='Save [Alt+S]']")
	   private WebElement saveBtn;

	   
	   public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}

	public WebElement getSearchEdit() {
		return searchIndiffpageEdit;
	}
	   	   
	 public WebElement getLookupImgBtn() {
	return lookupImgBtn;
	}
	public WebElement getCreatecontactBtn() {
		return createNewcontactBtn;
	}

	public WebElement getLastnameEdit() {
		return lastnameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getSupportStartDateEdit() {
		return supportStartDateEdit;
	}

	public WebElement getSupportendDateEdit() {
		return supportendDateEdit;
	}

	
	  public void createContact(String lastname) { 
	  lastnameEdit.sendKeys(lastname);
	  saveBtn.click(); }
	 
	public void createContactWithSupportDate(String lastname,String startDate, String endDate) {
		lastnameEdit.sendKeys(lastname);
		supportStartDateEdit.clear();
		supportStartDateEdit.sendKeys(startDate);
		supportendDateEdit.clear();
		supportendDateEdit.sendKeys(endDate);
		saveBtn.click();
	}
	public void createOrgcontact(String lastname,String orgName) {
		lastnameEdit.sendKeys(lastname);
		lookupImgBtn.click();
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.switchToNewBrowserTabOnUrl(driver, "module=Accounts");
		searchIndiffpageEdit.sendKeys(orgName);
		searchNowBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		wlib.switchToTabOnTitle(driver, "Contacts&action");
		saveBtn.click();
		
		}
	
	 }
