/* Author: truonganhdung
 * Created Date: 10/05/2018
 * Modified Date: 10/05/2018
 * */

package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_HandleDropdown_CustomDropdown {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled = false)
	public void TC_01_Handle_HTMLDropdownList() {
		driver.get("http://daominhdam.890m.com/");
		Select JobRole01 = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(JobRole01.isMultiple());

		JobRole01.selectByVisibleText("Automation Tester");
		Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(), "Automation Tester");

		JobRole01.selectByValue("manual");
		Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(), "Manual Tester");

		JobRole01.selectByIndex(3);
		Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(), "Mobile Tester");

		Assert.assertEquals(JobRole01.getOptions().size(), 5);
	}
	
	@Test(enabled=false)
	public void TC_02_Handle_JqueryDropdownList() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		/*
		driver.findElement(By.xpath("//span[@id='salutation-button']")).click();
		List <WebElement> list = driver.findElements(By.xpath("//ul[@id='salutation-menu']/li[@class='ui-menu-item']"));
		int number = list.size();
		for (int i = 0; i < number; i++) {
			if (list.get(i).getText().equals(expectedItem)){
				list.get(i).click();
				break;
			}
		}
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text' and text()='" + expectedItem + "']")).isDisplayed());
	 	*/
		
		//jqueryui
		vefDrop("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li[@class='ui-menu-item']", "Other");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text' and text()='Other']")).isDisplayed());
	}

	@Test(enabled = false)
	public void TC_03_Handle_AngularDropdownList() throws InterruptedException {
		driver.get("https://material.angular.io/components/select/examples");

		//angularjs
		vefDrop("//mat-select[@placeholder='State']", "//mat-option//span", "Nevada");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Nevada']")).isDisplayed());
	}

	@Test(enabled = false)
	public void TC_04_Handle_Kendo() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		
		//kendoui
		vefDrop("//input[@id='color']/preceding-sibling::span", "//ul[@id='color_listbox']/li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Grey']")).isDisplayed());
	}
	
	@Test(enabled = true)
	public void TC_05_Handle_Vue() throws InterruptedException {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		
		
		//vue-dropdowns
	//	vefDrop("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", value);
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).isDisplayed());
		
		WebElement parantDropDown = driver.findElement(By.xpath("//li[@class='dropdown-toggle']"));
		parantDropDown.click();
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", parantDropDown);
		
		List <WebElement> allItemsDropDown = driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li/a"));
		int itemNumber = allItemsDropDown.size();
		wait.until(ExpectedConditions.visibilityOfAllElements(allItemsDropDown));
		for (int i = 0; i < itemNumber; i++) {
			if (allItemsDropDown.get(i).getText().equals(expectedValue)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItemsDropDown.get(i));
				Thread.sleep(1000);
				allItemsDropDown.get(i).click();
				System.out.println(expectedValue);
				break;
			}
		}
		
		
		
	}
	
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	//( ._.')----------------------------------------------------
	public void vefDrop(String parentLocator, String allItemsLocator, String expectedValue) throws InterruptedException  {
		// 1 - Scroll to drop-down list then Click **parent locator** to expand all drop-down list
		WebElement parantDropDown = driver.findElement(By.xpath(parentLocator));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", parantDropDown);
		//wait.until(ExpectedConditions.elementToBeClickable(parantDropDown));
		Thread.sleep(1000);
		
		// 2 - get all items in list that have the same tag-name
		List <WebElement> allItemsDropDown = driver.findElements(By.xpath(allItemsLocator));
		int itemNumber = allItemsDropDown.size();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(allItemsDropDown));
		
		// 3 - get text of all items
		// 4 - compare w' text that input
		// 5 - click if meet condition
		for (int i = 0; i < itemNumber; i++) {
			if (allItemsDropDown.get(i).getText().equals(expectedValue)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItemsDropDown.get(i));
				Thread.sleep(1000);
				allItemsDropDown.get(i).click();
				break;
			}
		}
		Thread.sleep(1000);
	}

}
