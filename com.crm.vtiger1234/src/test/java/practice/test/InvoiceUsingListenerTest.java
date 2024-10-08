package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.comcast.crm.basetest.BaseClass;
@Listeners(com.comcast.crm.listenerutility.ListenerImplementationClass.class)
public class InvoiceUsingListenerTest extends BaseClass {
	@Test
	public void createInvoice() {
		System.out.println("execute createInvoice");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}
	@Test
	public void createInvoicewithContactTest() {
		System.out.println("execute createInvoicewithContactTest");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
