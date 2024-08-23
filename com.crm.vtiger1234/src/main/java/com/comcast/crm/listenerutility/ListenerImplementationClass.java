package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.BaseClassFinder;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementationClass implements ISuiteListener,ITestListener{
	public ExtentReports report;
    ExtentTest test;//by using this we are going to take the screenshot
	//public static ExtentReports report;//create static to use this in every test case using this class   

	@Override
	 public void onStart(ISuite suite) {
		  System.out.println("Report configuration");
		//spark report config
		  String time=new Date().toString().replace(" ", "_").replace(":", "_");
		  ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
			spark.config().setDocumentTitle("CRM test suite result");
			spark.config().setReportName("CRM report");
			spark.config().setTheme(Theme.DARK);
	 		//add env info and create test
			report= new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("OS","Windows-11");
			report.setSystemInfo("BROWSER","CHROME-100");
	  } 
	@Override
	  public void onFinish(ISuite suite) {
		  System.out.println("Report backup");
		  report.flush();
	 
	  } 
	@Override
	  public void onTestStart(ITestResult result) {
		  //it is display the name of the test case in console
		  System.out.println("====="+result.getMethod().getMethodName()+"====START======");
		  test = report.createTest(result.getMethod().getMethodName());
		  UtilityClassObject.setTest(test);
		  test.log(Status.INFO, result.getMethod().getMethodName()+"====>STARTED<=====");
	  } 
	@Override
	  public void onTestSuccess(ITestResult result) {
		  //this test case is ended(sucessfull)
		  System.out.println("====="+result.getMethod().getMethodName()+"=====END=====");
		  test.log(Status.INFO, result.getMethod().getMethodName()+"====>COMPLETED<====="); 
	  
	  } 
	@Override
	  public void onTestFailure(ITestResult result) {
		  //copy the screenshot of fail test case
		  String testName=result.getMethod().getMethodName();
			/*
			 * EventFiringWebDriver edriver =new EventFiringWebDriver(BaseClass.sdriver );
			 * File srcFile = edriver.getScreenshotAs(OutputType.FILE);
			 */	
		  TakesScreenshot edriver = (TakesScreenshot)UtilityClassObject.getDriver();
		  String filePath=edriver.getScreenshotAs(OutputType.BASE64);
		  
		  String time=new Date().toString().replace(" ", "_").replace(":", "_");
		  test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		  test.log(Status.INFO,result.getMethod().getMethodName()+"====>FAILED<====");

			/*
			 * try { FileUtils.copyFile(srcFile, new
			 * File("./screenshot/"+testName+""+time+".png")); } catch (IOException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */	  }	
	public void onTestSkipped(ITestResult result) {
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	public void OnStart(ITestContext context) {
		
	}
	public void OnFinish(ITestContext context) {
		
	}
}

