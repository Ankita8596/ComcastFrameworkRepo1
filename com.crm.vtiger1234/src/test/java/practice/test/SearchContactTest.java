package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for Contact module
 * @author itsab
 */

public class SearchContactTest extends BaseClass{
	/**
	 * Scenario : login()==> navigateContact==>createcontact()==verify(give high level info)
	 */
	@Test
	public void searchcontactTest() {
		/* step 1: login to application*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp("url", "username", "password");
		
	}

}
