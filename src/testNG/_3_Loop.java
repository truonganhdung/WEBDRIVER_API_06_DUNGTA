package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class _3_Loop {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(invocationCount = 3)
	public void TC() {
		driver.get("http://demo.guru99.com/v4/");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr161493");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("harErAh");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		driver.findElement(By.xpath("//a[text()='Log out']")).click();
		driver.switchTo().alert().accept();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
