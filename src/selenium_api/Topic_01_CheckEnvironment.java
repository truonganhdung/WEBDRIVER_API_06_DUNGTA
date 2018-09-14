package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnvironment {
	WebDriver driver;
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }  
  
  @Test
  public void TC_01_CheckBrower() {
	  driver.get("https://live.guru99.com");
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Home page"); 
  }


  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
