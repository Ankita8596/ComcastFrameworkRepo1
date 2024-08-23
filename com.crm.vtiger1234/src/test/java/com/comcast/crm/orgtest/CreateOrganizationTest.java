package com.comcast.crm.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListenerImplementationClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.listenerutility.ListenerImplementationClass.class )
public class CreateOrganizationTest extends BaseClass{

	/*
	 * public static void main(String[] args) throws IOException { //READ DATA FROM
	 * PROPERTY FILE FileUtility flib =new FileUtility(); ExcelUtility elib = new
	 * ExcelUtility(); WebDriverUtility wlib = new WebDriverUtility(); String
	 * BROWSER =flib.getDataFromProertyFile("browser"); String URL
	 * =flib.getDataFromProertyFile("url"); String USERNAME
	 * =flib.getDataFromProertyFile("username"); String PASSWORD
	 * =flib.getDataFromProertyFile("password"); //random number utility object is
	 * created JavaUtility jlib = new JavaUtility(); //READ DATA FROM EXCEL SHEET
	 */		
	@Test(groups="smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException {
	UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
	String orgName =elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumer();//random number object is concatnated with excel data
	/*
	 * WebDriver driver = null;
	 * 
	 * if(BROWSER.equals("chrome")) { driver = new ChromeDriver(); } else
	 * if(BROWSER.equals("edge")) { driver = new EdgeDriver(); } else { driver = new
	 * ChromeDriver(); } wlib.waitForPageToLoad(driver);
	 * //wlib.maximizingThewindow(driver); driver.get(URL); //login to application
	 * LoginPage login=new LoginPage(driver); login.loginToapp(URL,USERNAME,
	 * PASSWORD);
	 */
		//navigate to the organization module
	    UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		//hp.navigateTocampaignLink();
		
		//click on create org button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage cpn = new OrganizationsPage(driver);
		cpn.getCreateNewOrgBtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+ "create a new org");
		
		//verify the header msg using POM class
 		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(orgName));
			}
	@Test(groups="regressionTest")
	public void CreateOrganizationWithIndustriesTest() throws EncryptedDocumentException, IOException {
		String orgName = elib.getDataFromExcel("org", 1, 2) +jlib.getRandomNumer();
		String industry =elib.getDataFromExcel("org", 4, 3);//because it is a drop down data(static data)
		String type =elib.getDataFromExcel("org", 4, 4);
		//achiving the polymorphism to run the script in diff. differnt browser
		//launch the browser
		//finding the elements using locator
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp =new HomePage(driver);
		hp.getOrgLink().click();
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "creating the new organization");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);
		
		//select the industry and its type		
		
		//verify the orgname
		/*
		 * String
		 * headreInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).
		 * getText(); if(headreInfo.contains(orgName)) { System.out.println(orgName +
		 * "is created==Pass"); } else { System.out.println(orgName +
		 * "is not created==Fail"); }
		 */
		//verify the industries and type info
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headreInfo=oip.getOrgheaderMsg().getText();
		if(headreInfo.contains(orgName)) {
			System.out.println(orgName + "is created==Pass");
		}
		else {
			System.out.println(orgName + "is not created==Fail");
		}
		//verify the industries and type info
		String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustries.equals(industry)) {
			System.out.println(industry + "is verified==Pass");
		}
		else {
			System.out.println(orgName + "is not verified==Fail");
		}
		//verify header orgname info expected result
		String acttype = driver.findElement(By.id("dtlview_Type")).getText();
		if(acttype.equals(type)) {
			System.out.println(type + "is verified==Pass");
		}
		else {
			System.out.println(type + "is not verified==Fail");
		}
	}
		@Test(groups="regressionTest")
		public void CreateOrganizationWithPhoneNumber() throws EncryptedDocumentException, IOException {		
			//READ DATA FROM EXCEL SHEET
			String orgName =elib.getDataFromExcel("org",1,2) +jlib.getRandomNumer();
			String phoneNum =elib.getDataFromExcel("org",7,3);
		    HomePage hp = new HomePage(driver);
		    hp.getOrgLink().click();
			OrganizationsPage op = new OrganizationsPage(driver);
			op.getCreateNewOrgBtn().click();
			CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
			cnop.createOrgWithPhone(orgName, phoneNum);
			
			//verify phone number  Expected result
			String actphonenumber=driver.findElement(By.id("dtlview_Phone")).getText();
					if(actphonenumber.equals(phoneNum)) {
						System.out.println(phoneNum+ "infomation is created==Pass");
					}
					else {
						System.out.println(phoneNum+ "infomation is not created==Fail");
					}
		}

			
		}




 