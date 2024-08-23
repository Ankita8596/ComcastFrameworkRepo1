package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetDataFromExcelUsingDataProvider {
	@Test(dataProvider = "getData")
	public void getProductInfodataTest(String brandname,String productname) {
		WebDriver  driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname,Keys.ENTER);
		//capture product info
	String x="//span[text()='"+productname+"']/../../../../..//span[text()='52,999']";
				String price=driver.findElement(By.xpath(x)).getText();
				System.out.println(price);			
	}
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		ExcelUtility elib = new ExcelUtility();
		int rowCount=elib.getRowCount("product");
		Object[][] objarr =new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
			objarr[i][0]=elib.getDataFromExcel("product", i+1, 0);
			objarr[i][1]=elib.getDataFromExcel("product", i+1, 1);

		}
		return objarr;
		
	}
	}



