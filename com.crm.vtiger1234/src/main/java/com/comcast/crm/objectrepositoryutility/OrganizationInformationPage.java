package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement OrgheaderMsg;

	public WebElement getOrgheaderMsg() {
		return OrgheaderMsg;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

}
