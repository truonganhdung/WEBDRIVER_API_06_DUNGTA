/* Author: truonganhdung
 * Created Date: 10/13/2018
 * Modified Date: 10/15/2018
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
	@Test(enabled=false)
	public void TC_01(){
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
		WebElement banners = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		Assert.assertTrue(banners.isDisplayed());
		driver.switchTo().frame(banners);
		
		List<WebElement> list6Banners= driver.findElements(By.xpath("//div[@class='bannerimage-container']"));
		Assert.assertEquals(list6Banners.size(),6);
		
		driver.switchTo().defaultContent();
		
		/*-- Step 05 --*/
		List<WebElement> list8Icons= driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(list8Icons.size(),8);
		
	}

	@Test(enabled=true)
	public void TC_02(){
		driver.get("http://daominhdam.890m.com/");
		
		String link = driver.findElement(By.xpath("//a[text()='Click Here']")).getAttribute("href");
		driver.get(link);
		String newTab = driver.getTitle();
		Assert.assertEquals(newTab, "Google");
		
		driver.navigate().back();
	}
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
