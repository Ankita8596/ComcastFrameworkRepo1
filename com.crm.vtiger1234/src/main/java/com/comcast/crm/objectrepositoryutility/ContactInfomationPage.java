package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfomationPage {
	
	
	WebDriver driver;
	public ContactInfomationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startdateMsg;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement enddateMsg;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactinfo;
	
	/*
	 * @FindBy(id="dtlview_Last Name") private WebElement headerlastnameEdit;
	 * 
	 * public WebElement getLastnameEdit() { return headerlastnameEdit; }
	 */
	public WebElement getContactinfo() {
		return contactinfo;
	}
		
	public WebElement getDateMsg() {
		return startdateMsg;
	}
	public WebElement getEnddateMsg() {
		return enddateMsg;
	}
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	

}
