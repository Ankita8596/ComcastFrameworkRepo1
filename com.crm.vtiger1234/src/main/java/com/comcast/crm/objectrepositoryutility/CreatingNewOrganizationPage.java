package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement idustryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	@FindBy(name="phone")
	private WebElement phoneEdit;
	
	public WebElement getPhoneEdit() {
		return phoneEdit;
	}
	
	public void setPhoneEdit(WebElement phoneEdit) {
		this.phoneEdit = phoneEdit;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createOrg(String orgname) {
		orgNameEdit.sendKeys(orgname);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String industry,String type) {
		orgNameEdit.sendKeys(orgName);
		Select sel = new Select(idustryDD);
		sel.selectByVisibleText(industry);
		Select sel1 = new Select(typeDD);
		sel1.selectByVisibleText(type);
		saveBtn.click();
	}	
	public void createOrgWithPhone(String orgName, String phone) {
		orgNameEdit.sendKeys(orgName);
		phoneEdit.sendKeys(phone);
		saveBtn.click();
	}
}
