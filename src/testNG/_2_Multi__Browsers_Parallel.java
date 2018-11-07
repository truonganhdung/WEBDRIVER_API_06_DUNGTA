package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class _2_Multi__Browsers_Parallel {
	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		if (browserName.equals("firefox")) {
		    driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
		    System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		    driver = new ChromeDriver();
		} else {
		    System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("headless");
		    options.addArguments("window-size=1920x1080");
		    driver = new ChromeDriver(options);
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}
	
	@Parameters({"sUser", "sPass" })
	@Test()
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
