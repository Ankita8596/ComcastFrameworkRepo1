package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author itsab
 * Contains login page element and business library like login() (//java doc)
 */
///Rule1:Create separate class
public class LoginPage extends WebDriverUtility{
	WebDriver driver;
	//create constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Rule 2: identify all the element using @find by(object creation)
	@FindBy(name="user_name")
	private WebElement usernameEdit;//public it means any one modify the elements so we can not give public access modifier
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	//Rule 3:object Initialization in test script 
	
	//Rule 4:Object encapsulation

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	/**
	 * login to application based on  username , password, url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	
	// Rule 5:Provide action
	public void loginToapp(String url,String username,String password) {//business method specific to the script
		driver.manage().window().maximize();
		waitForPageToLoad(driver);
		driver.get(url);
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}
	
	
}
