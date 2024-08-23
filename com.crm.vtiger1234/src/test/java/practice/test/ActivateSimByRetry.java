package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class ActivateSimByRetry{
	@Test(retryAnalyzer= com.comcast.crm.listenerutility.RetryListenerImplementation.class)
	public void activateSim() {
		System.out.println("execute createInvoice");
		//String actTitle = driver.getTitle();
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}


}
