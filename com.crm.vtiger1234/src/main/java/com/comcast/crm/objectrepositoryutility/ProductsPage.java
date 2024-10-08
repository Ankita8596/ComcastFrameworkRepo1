package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage {
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createproductimgBtn;
	
	@FindBy(name="searchBtn")
	private WebElement ele1;

	public WebElement getEle1() {
		return ele1;
	}

	@FindBy(name="search")
	private WebElement ele3;


	public WebElement getCreateproductimgBtn() {
		return createproductimgBtn;
	}

	public WebElement getEle3() {
		return ele3;
	}
}
