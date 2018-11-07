package testNG;

import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class _1_DataProvider {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@DataProvider(name = "User/Pass")
	public static Object[][] userAndPass() {
		return new Object[][] { 
			{ "mngr139454", "qwerty1!" }
			, { "mngr161493", "harErAh" } 
		};
	}

	@Test(dataProvider = "User/Pass")
	public void TC01(String sUser, String sPass) {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(sUser);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(sPass);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		// Verify Home page display
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Log out']")).click();
		driver.switchTo().alert().accept();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
