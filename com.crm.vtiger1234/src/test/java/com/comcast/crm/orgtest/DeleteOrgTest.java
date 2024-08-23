package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws IOException {
		FileUtility flib =new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		String BROWSER =flib.getDataFromProertyFile("browser");
		String URL =flib.getDataFromProertyFile("url");
		String USERNAME =flib.getDataFromProertyFile("username");
		String PASSWORD =flib.getDataFromProertyFile("password");
		//random number utility object is created
		JavaUtility jlib = new JavaUtility();
		//READ DATA FROM EXCEL SHEET                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		String orgName =elib.getDataFromExcel("org", 10 , 2)+jlib.getRandomNumer();//random number object is concatnated with excel data
		WebDriver driver = null;
		
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		wlib.waitForPageToLoad(driver);
		//wlib.maximizingThewindow(driver);
		driver.get(URL);
		//login to application
		LoginPage login=new LoginPage(driver);
		login.loginToapp(URL,USERNAME, PASSWORD);
		//navigate to the organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		//hp.navigateTocampaignLink();
		
		//click on create org button
		OrganizationsPage cpn = new OrganizationsPage(driver);
		cpn.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgName);
		
		//verify the header msg using POM class
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+"name is verified===PASS");
		}
		else {
			System.out.println(orgName+ "name is verified===FAIL");
		}
		//go back to organization page 
		//HomePage hp1 = new HomePage(driver);
		hp.getOrgLink().click();
		cpn.getSearchEdit().sendKeys(orgName);
		wlib.selectByElement(cpn.getSearchDD(), "Organization Name");
		cpn.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		wlib.switchToAlert(driver);
		//search for Org
		//In dynamic web table select and delete org
				hp.logout();
	}

}

