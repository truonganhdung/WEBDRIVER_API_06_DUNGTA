package guru99.II_VII_POM.testCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import guru99.II_VII_POM.pageFactory.Guru99HomePage_PageFactory;
import guru99.II_VII_POM.pageFactory.Guru99Login_PageFactory;

public class Test99GuruLoginWithPageFactory {

	WebDriver driver;
	Guru99Login_PageFactory objLogin;
	Guru99HomePage_PageFactory objHomePage;
	
	//https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html
	@BeforeTest
	public void setup(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/V4/");
	}

	/**
	 * This test go to http://demo.guru99.com/V4/
	 * Verify login page title as guru99 bank
	 * Login to application
	 * Verify the home page using Dashboard message
	 */
	@Test(priority=0)
	public void test_Home_Page_Appear_Correct(){
		objLogin = new Guru99Login_PageFactory(driver);

		String loginPageTitle = objLogin.getLoginTitle();
		Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

		objLogin.loginToGuru99("mgr123", "mgr!23");
		
		objHomePage = new Guru99HomePage_PageFactory(driver);
		Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
	}
}