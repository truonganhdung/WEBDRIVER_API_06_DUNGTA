/* Author: truonganhdung
 * Created Date: 10/13x/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Iframe {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_() throws Exception {
		/*-- Step 01 --*/
		driver.get("http://www.hdfcbank.com/");
		
		/*-- Step 02 --*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<WebElement> popAds = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (popAds.size() > 0) {
			driver.switchTo().frame(popAds.get(0));
			WebElement iconX = driver.findElement(By.xpath("//div[@id='div-close']"));
			
			Assert.assertTrue(iconX.isDisplayed());
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", iconX);
			
			Assert.assertFalse(iconX.isDisplayed());
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.switchTo().defaultContent();
		
		/*-- Step 03 --*/
		WebElement looking = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(looking);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		
		/*-- Step 04 --*/
		List<WebElement> banners = driver.findElements(By.xpath("//div[@id='bannercontainer']"));
		driver.switchTo().frame(banners);
		Assert.assertTrue(banners.isDisplayed());
		
	}

	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
