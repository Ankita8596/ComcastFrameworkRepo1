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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithPhoneNumber {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileUtility flib= new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		String BROWSER = flib.getDataFromProertyFile("browser");
		String URL = flib.getDataFromProertyFile("url");
		String USERNAME = flib.getDataFromProertyFile("username");
		String PASSWORD = flib.getDataFromProertyFile("password");
		
		JavaUtility jlib = new JavaUtility();		
		//READ DATA FROM EXCEL SHEET
		String orgName =elib.getDataFromExcel("org",1,2) +jlib.getRandomNumer();
		String phoneNum =elib.getDataFromExcel("org",7,3);
	
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
		wlib.maximizingThewindow(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(phoneNum);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify orgname
		String actorgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actorgName.equals(orgName)) {
			System.out.println(orgName + "is created==Pass");
		}
		else {
			System.out.println(orgName + "is not created==Fail");
		}
		//verify phone number  Expected result
		String actphonenumber=driver.findElement(By.id("dtlview_Phone")).getText();
				if(actphonenumber.equals(phoneNum)) {
					System.out.println(phoneNum+ "infomation is created==Pass");
				}
				else {
					System.out.println(phoneNum+ "infomation is not created==Fail");
				}
		
		driver.quit();	
		
	}
	}
