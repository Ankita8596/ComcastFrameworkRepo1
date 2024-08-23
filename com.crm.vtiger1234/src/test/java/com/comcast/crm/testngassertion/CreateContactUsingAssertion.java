package com.comcast.crm.testngassertion;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfomationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

import junit.framework.Assert;

public class CreateContactUsingAssertion extends BaseClass{
	@Test
	public void CreateContactUsingAssertionTest() throws EncryptedDocumentException, IOException {
		String lastname = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumer();
		//navigate to home page
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		//click on contact create contact page
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreatenewContactBtn().click();
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastname);
		String actHeader = cp.getHeaderMsg().getText();	
		boolean status=actHeader.contains(lastname);
		Assert.assertEquals(status, true);
		String actLastName=cp.getLastnameEdit().getText();
		SoftAssert soft= new SoftAssert();
		soft.assertEquals(actHeader, actLastName);
		
	}

}
