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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustriesTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		String BROWSER =flib.getDataFromProertyFile("browser");
		String URL =flib.getDataFromProertyFile("url");
		String USERNAME =flib.getDataFromProertyFile("username");
		String PASSWORD =flib.getDataFromProertyFile("password");

		
		//READ DATA FROM EXCEL SHEET
		String orgName = elib.getDataFromExcel("org", 1, 2) +jlib.getRandomNumer();
		String industry =elib.getDataFromExcel("org", 4, 3);//because it is a drop down data(static data)
		String type =elib.getDataFromExcel("org", 4, 4);
		//achiving the polymorphism to run the script in diff. differnt browser
		//launch the browser
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
		//finding the elements using locator
		wlib.waitForPageToLoad(driver);
		wlib.maximizingThewindow(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//select the industry and its type
		WebElement wbsele = driver.findElement(By.name("industry"));
		wlib.selectByElement(wbsele, industry);
		WebElement wbsel = driver.findElement(By.name("accounttype"));
		wlib.selectByElement(wbsel, type);		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the orgname
		String headreInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
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
		driver.quit();
		
		 
	}

}

