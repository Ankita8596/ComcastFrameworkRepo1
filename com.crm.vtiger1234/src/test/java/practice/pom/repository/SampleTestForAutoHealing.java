package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBys;

import org.testng.annotations.Test;

public class SampleTestForAutoHealing {
			@FindBy(name="user_name")
		WebElement ele1;
		
		@FindBy(name="user_password")
		WebElement ele2;
		
		@FindAll({@FindBy(id="sub"),@FindBy(xpath="//input[@value='submit']")})	
		private WebElement ele3;
		
		@Test
		public void sampleTest() {
			WebDriver driver = new ChromeDriver();
			driver.get("http://localhost:8888/");
			
			SampleTestForAutoHealing sa = PageFactory.initElements(driver,SampleTestForAutoHealing.class);
			sa.ele1.sendKeys("admin");
			sa.ele2.sendKeys("admin");
			sa.ele3.click();
		}

	}


