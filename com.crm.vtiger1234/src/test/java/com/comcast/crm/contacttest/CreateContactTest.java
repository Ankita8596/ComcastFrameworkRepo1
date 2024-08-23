package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfomationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
/**
 * @author itsab
 */
public class CreateContactTest extends BaseClass {
	@Test(groups="smokeTest")
	public void createContactTest() throws IOException {
		/*step 1:read testScript data from Excel file*/
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumer();
        /*navigate to home page*/
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		 /*step 2: click on "create contact" Button*/
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreatenewContactBtn().click();
		 /*step 3: enter all the details*/
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);
		// verify contact name with expected result
		String actlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actlastname.equals(lastName)) {
			System.out.println(lastName + "is created==Pass");
		} else {
			System.out.println(lastName + "is not created==Fail");
		}
	}

     @Test(groups="regressionTest")
     public void CreateAContactWithSupportDateTest() throws EncryptedDocumentException, IOException {
    	String lastname = elib.getDataFromExcel("contact", 1, 2) +jlib.getRandomNumer();
 		//String phoneNum = row.getCell(3).getStringCellValue();
    	HomePage hp = new HomePage(driver);
    	hp.getContactLink().click();
 		ContactsPage cp = new ContactsPage(driver);
 		cp.getCreatenewContactBtn().click();
 		//String endDate=jlib.getSystemDateYYYYDDMM();
 		String startDate=jlib.getSystemDateyyyyMMdd();
 		String endDate=jlib.getRequiredDateyyyyMMdd(30);		
 		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
 		cncp.createContactWithSupportDate(lastname, startDate, endDate);
 		//driver.findElement(By.id("phone")).sendKeys(phoneNum);
 		ContactInfomationPage cip = new ContactInfomationPage(driver);
 		String actstartdate =cip.getDateMsg().getText();
 		if(actstartdate.equals(startDate)) {
			System.out.println(startDate + "is created==Pass");
		}
		else {
			System.out.println(startDate + "is not created==Fail");
		}
		String actenddate =cip.getEnddateMsg().getText();
		if(actenddate.equals(endDate)) {
			System.out.println(endDate + "infomation is verified==Pass");
		}
		else {
			System.out.println(endDate + "infomation is not verified==Fail");
		}
     }

	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {
										
		//READ DATA FROM EXCEL SHEET
		String orgName =elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumer();
		String contactLastName =elib.getDataFromExcel("contact", 1, 2);
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrgNameEdit().click();
		cnop.createOrg(orgName);
		
		//verify pre-condition given into the test case
		
		  OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		  String headreInfo=oip.getOrgheaderMsg().getText();
			/*
			 * if(headreInfo.contains(orgName)) { System.out.println(orgName
			 * +"is created==Pass"); } else { System.out.println(orgName
			 * +"is not created==Fail"); }
			 */		 
		//step 5 navigate to contact module
		hp.getContactLink().click();
		//step 6:click on create contact button
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getCreatecontactBtn().click();
		//driver.findElement(By.id("phone")).sendKeys(phoneNum);
		//switch to child window
		cncp.createOrgcontact(contactLastName, orgName);
		//verify header msg in contact
		ContactInfomationPage cip = new ContactInfomationPage(driver);
		headreInfo =cip.getContactinfo().getText();
		if(headreInfo.contains(contactLastName)) {
			System.out.println(contactLastName + "is created==Pass");
		}
		else {
			System.out.println(contactLastName + "is not created==Fail");
		}
		//verify header msg expected result
		String actorgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actorgName);
		
		if(actorgName.trim().equals(orgName)) {
			System.out.println(orgName + "is created==Pass");
		}
		else {
			System.out.println(orgName + "is not created==Fail");
		}
		
}

}
