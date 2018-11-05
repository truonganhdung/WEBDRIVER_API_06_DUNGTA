/* Author: truonganhdung
 * Created Date: 11/04/2018
 * Modified Date: 11/05/2018
 * */

package selenium_api;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.common.base.Function;


public class Topic_13_Wait {
    WebDriver driver;
    WebDriverWait waitExplicit;
    By selectedDate = By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']");

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		waitExplicit = new WebDriverWait(driver, 30);
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=false)
	public void TC_01_ImplicitlyWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test(enabled=false)
	public void TC_02_ExplicitWait() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Check Date Picker display
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")).isDisplayed());
		
		//Verify selected date = "No Selected Dates to display."
		WebElement selectedDateBefore = driver.findElement(selectedDate);
		Assert.assertEquals(selectedDateBefore.getText(), "No Selected Dates to display.");
		
		//pick 1 random day
		driver.findElement(By.xpath("//a[text()='16']")).click();
		
		//skip ajax loading
		By ajaxIcon = By.xpath("//div[@class='raDiv']");
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(ajaxIcon));
		
		//Verify selected date = 16 in Date Picker
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='16']")).isDisplayed());
		
		//Verify selected date = "Friday, November 16, 2018"
		WebElement selectedDateAfter = driver.findElement(selectedDate);
		Assert.assertEquals(selectedDateAfter.getText(), "Friday, November 16, 2018");
	}
	
	@Test(enabled=true)
	public void TC_03_FluentWait() {
		driver.get("https://daominhdam.github.io/fluent-wait/");
		
		WebElement countdount =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		//Assert.assertTrue(countdount.isDisplayed());
		waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

		new FluentWait<WebElement>(countdount)
		.withTimeout(5, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag =  element.getText().endsWith("00");
				System.out.println("Time = " +  element.getText());
				return flag;
			}
		});
	}

	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
