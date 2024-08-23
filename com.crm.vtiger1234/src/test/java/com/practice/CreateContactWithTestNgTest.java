package com.practice;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreateContactWithTestNgTest {
	@BeforeSuite
	public void configBS() {
		System.out.println("execute BS");
	}
	@AfterSuite
	public void configAfterSuite() {
		System.out.println("AfterSuite");
	}


	@BeforeClass
	public void configBC() {
		System.out.println("execute BC");
	}
	@Test 
	public void createContact() {
		System.out.println("execute create contact");
	}
	@Test 
	public void createContactWithDate() {
		System.out.println("execute create contact with date");
	}

	@BeforeMethod 
	public void configBM() {
		System.out.println("execute BM");
	}
	@AfterMethod
	public void configAfterMethod() {
		System.out.println("aftermethod");
	}
	
	@AfterClass
	public void configAfterClass() {
		System.out.println("AfterClass");
	}
	
	



}
