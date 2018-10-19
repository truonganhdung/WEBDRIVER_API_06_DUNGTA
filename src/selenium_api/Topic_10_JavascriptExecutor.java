/* Author: truonganhdung
 * Created Date: 10/19/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

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

public class Topic_10_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor js;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		js = (JavascriptExecutor) driver;
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=false)
	public void TC_01_JavascriptExcecutor() {
		driver.get("http://live.guru99.com/");
		
		//Step 02: Get domain
		String domain = (String)js.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.guru99.com");
		
		//Step 03: Get URL
		String urlPage = (String)js.executeScript("return document.URL");
		Assert.assertEquals(urlPage, driver.getCurrentUrl());
		
		//Step 04: Open MOBILE page
		WebElement element = driver.findElement(By.xpath("//a[text()='Mobile']"));
		js.executeScript("arguments[0].style.border='2px groove green'", element);
		js.executeScript("arguments[0].click();", element);
		
		//Step 05: Add SAMSUNG GALAXY to Cart 
		WebElement cartSS = driver.findElement(By.xpath("//div[h2[a[text()='Samsung Galaxy']]]//span[text()='Add to Cart']"));
		js.executeScript("arguments[0].click();", cartSS);
		
		//Step 06: Verify "Samsung Galaxy was added to your shopping cart."
		String addSucceed = driver.findElement(By.xpath("//span[text()='Samsung Galaxy was added to your shopping cart.']")).getText();
		String sText = js.executeScript("return document.documentElement.innerText;").toString();
		String[] arrtemp = sText.split("[\\r\\n]+");
		for(String a: arrtemp) {
			if(a.equals(addSucceed)) {
				System.out.println("Found");
				break;
			}
		}
		
		//Step 07: Open PRIVACY POLICY page
		WebElement pPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		js.executeScript("arguments[0].click();", pPolicy);
		String sTitle =  js.executeScript("return document.title;").toString();
		Assert.assertEquals(sTitle, driver.getTitle());
		
		//Step 08-09: Scroll to bottom page
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='The number of items in your Wishlist.']")).isDisplayed());
		
		//Step 10: navigate to new domain
		js.executeScript("window.location = 'http://demo.guru99.com/v4/'");
		String domainNavigate = (String)js.executeScript("return document.domain");
		Assert.assertEquals(domainNavigate, "demo.guru99.com");
	}

	@Test(enabled=true)
	public void TC_02_RemoveAttribute() {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		
		driver.switchTo().frame("//iframe[@id='iframeResult']");
		
		//Step 02: Remove disabled
		WebElement lName = driver.findElement(By.xpath("//input[@name='lname']"));
		js.executeScript("arguments[0].removeAttribute('disabled');", lName);
		
		//Step 03: Send key to Last name
		lName.sendKeys("Automation Testing");
		
		//Step 04: Press [Submit]
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
