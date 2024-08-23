package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrgnazationTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//READ DATA FROM PROPERTY FILE
				FileUtility flib = new FileUtility();
				ExcelUtility elib = new ExcelUtility();
				WebDriverUtility wlib = new WebDriverUtility();
				JavaUtility jlib = new JavaUtility();
				String BROWSER =flib.getDataFromProertyFile("browser");
				String URL = flib.getDataFromProertyFile("url");
				String USERNAME = flib.getDataFromProertyFile("username");
				String PASSWORD =flib.getDataFromProertyFile("password");
								
				//READ DATA FROM EXCEL SHEET
				String orgName =elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumer();
				String contactLastName =elib.getDataFromExcel("contact", 1, 2);
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
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify pre-condition given into the test case
				String headreInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headreInfo.contains(orgName)) {
					System.out.println(orgName + "is created==Pass");
				}
				else {
					System.out.println(orgName + "is not created==Fail");
				}
				//step 5 navigate to contact module
				driver.findElement(By.linkText("Contacts")).click();
				//step 6:click on create contact button
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(contactLastName);
				//driver.findElement(By.id("phone")).sendKeys(phoneNum);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				//switch to child window
				wlib.switchToNewBrowserTabOnUrl(driver, "module=Accounts");
				driver.findElement(By.name("search_text")).sendKeys(orgName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//dynamic xpath
				
				//switching to parent window
				wlib.switchToTabOnTitle(driver, "Contacts&action");				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				//verify header msg in contact
				headreInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
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
				
	          driver.quit();
	}

}
