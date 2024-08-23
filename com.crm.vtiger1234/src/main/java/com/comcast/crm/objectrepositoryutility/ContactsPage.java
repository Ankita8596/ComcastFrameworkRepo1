package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ContactsPage {
	WebDriver driver;
	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

		
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createnewContactBtn;
	
	@FindBy(name="search_text")
	private WebElement searchtextedit;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="search")
	private WebElement searchnowBtn;

	
	@FindBy(name="submit")
	private WebElement submitBtn;
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement lastnameEdit;
	
	public WebElement getLastnameEdit() {
		return lastnameEdit;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public void setHeaderMsg(WebElement headerMsg) {
		this.headerMsg = headerMsg;
	}

	public WebElement getCreatenewContactBtn() {
		return createnewContactBtn;
	}

	public WebElement getSearchtextedit() {
		return searchtextedit;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	public void searchText() {
		searchtextedit.click();
	}

}
