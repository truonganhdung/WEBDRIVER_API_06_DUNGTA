/* Author: truonganhdung
 * Created Date: 10/19/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.List;
import java.util.Set;
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

public class Topic_09_Windows {
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
	public void TC_01() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		String mainWindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		switchByID(mainWindow);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		driver.close();
		
		driver.switchTo().window(mainWindow);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}

	@Test(enabled=true)
	public void TC_02() {
		driver.get("http://www.hdfcbank.com/");
		String mainTitle = driver.getTitle();
		String mainWindow = driver.getWindowHandle();
		
		closePopupAds();
		
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		verifyWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		driver.findElement(By.xpath("//a[div/p[text()='Account Details']]")).click();
		switchByTitle("Welcome to HDFC Bank NetBanking");
		verifyWindowByTitle("Welcome to HDFC Bank NetBanking");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		verifyWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		switchByTitle("HDFC BANK - CSR - Homepage");
		verifyWindowByTitle("HDFC BANK - CSR - Homepage");
		
		closeAllWithoutParentWindows(mainWindow);
		verifyWindowByTitle(mainTitle);
	}
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//Only 2 window
	public void switchByID(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String findWindow: allWindows) {
			if(!findWindow.equals(parent)) {
				driver.switchTo().window(findWindow);
				break;
			}
		}
	}
	
	// >= 2 window
	public void switchByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String findWindow: allWindows) {
			driver.switchTo().window(findWindow);
			String currentWindow = driver.getTitle();
			if(currentWindow.equals(title)) {
				break;
			}
		}
	}
	
	public boolean closeAllWithoutParentWindows(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String runWindow: allWindows) {
			if(!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if(driver.getWindowHandles().size() == 1)
			return true;
		else return false;
	}
	
	public boolean verifyWindowByTitle(String title) {
		String tTitle = driver.getTitle();
		if (tTitle == title)
			return true;
		else return false;
	}
	
	public void closePopupAds() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<WebElement> popAds = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (popAds.size() > 0) {
			driver.switchTo().frame(popAds.get(0));
			WebElement iconX = driver.findElement(By.xpath("//div[@id='div-close']"));
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", iconX);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
	}
	
}
