package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateAContactWithSupportDateTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		String BROWSER = flib.getDataFromProertyFile("browser");
		String URL = flib.getDataFromProertyFile("url");
		String USERNAME = flib.getDataFromProertyFile("username");
		String PASSWORD = flib.getDataFromProertyFile("password");
		
		
		
		JavaUtility jlib = new JavaUtility();		
		//READ DATA FROM EXCEL SHEET
		String lastname = elib.getDataFromExcel("contact", 1, 2) +jlib.getRandomNumer();
		//String phoneNum = row.getCell(3).getStringCellValue();
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
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String startDate=jlib.getSystemDateyyyyMMdd();
 		String endDate=jlib.getRequiredDateyyyyMMdd(30);		
 		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		//driver.findElement(By.id("phone")).sendKeys(phoneNum);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify orgname
		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstartdate.equals(startDate)) {
			System.out.println(startDate + "is created==Pass");
		}
		else {
			System.out.println(startDate + "is not created==Fail");
		}
		String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actenddate.equals(endDate)) {
			System.out.println(endDate + "infomation is verified==Pass");
		}
		else {
			System.out.println(endDate + "infomation is not verified==Fail");
		}
		
		
		driver.quit();	
		
	}
	}

