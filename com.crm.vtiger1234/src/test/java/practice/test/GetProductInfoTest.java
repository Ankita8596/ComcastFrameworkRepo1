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

public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandname,String productname) {
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
	public Object[][] getData() throws EncryptedDocumentException, IOException {
				Object[][] objarr =new Object[3][2];
		
		objarr[0][0]="iphone";
		objarr[0][1]="Apple iPhone 13 (128GB) - Midnight";
		
		objarr[1][0]="iphone";
		objarr[1][1]="Apple iPhone 13 (128GB) - Starlight";
		
		objarr[2][0]="iphone";
		objarr[2][1]="Apple iPhone 15 (128 GB) - Blue";

		return objarr;//always return the object array
	}
}



