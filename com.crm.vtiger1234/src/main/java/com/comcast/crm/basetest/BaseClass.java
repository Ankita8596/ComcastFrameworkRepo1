package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.databaseutility.DatabseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass{
	public DatabseUtility dblib = new DatabseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public  WebDriver driver = null;
	//public static WebDriver sdriver = null;
		
	@BeforeSuite
	public void configBS() throws SQLException {
		System.out.println("==Connect to db, report config==");
		dblib.getConnection();
	}
	//@Parameters("BROWSER")
	@BeforeClass(groups={"smokeTest","regressionTest"})
	public void configBC() throws IOException {
		System.out.println("==Launch the browser==");
		//String BROWSER=browser;
	String BROWSER=flib.getDataFromProertyFile("browser");
		
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		//sdriver=driver;
		UtilityClassObject.setDriver(driver);//in script call by get method
	}
	@BeforeMethod(groups={"smokeTest","regressionTest"})
	public void configBM() throws IOException {
		System.out.println("==login to application==");
		String URL = flib.getDataFromProertyFile("url");
		String USERNAME = flib.getDataFromProertyFile("username");
		String PASSWORD = flib.getDataFromProertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}
	@AfterMethod(groups={"smokeTest","regressionTest"})
	public void configAfterMethod() {
		System.out.println("==Logout from browser==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}


	@AfterClass(groups={"smokeTest","regressionTest"})

	public void configAC() {
		System.out.println("==close the browser==");
		driver.quit();
	}


	@AfterSuite(groups={"smokeTest","regressionTest"})
	public void configAS() throws SQLException {
		System.out.println("==Close dB report backup==");
		dblib.closeDbconnection();
	}

}
