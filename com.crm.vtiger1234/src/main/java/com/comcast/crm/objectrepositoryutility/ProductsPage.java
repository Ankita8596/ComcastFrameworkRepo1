package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage {
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createproductimgBtn;

	public WebElement getCreateproductimgBtn() {
		return createproductimgBtn;
	}

}
