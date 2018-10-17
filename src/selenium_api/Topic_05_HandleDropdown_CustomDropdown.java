/* Author: truonganhdung
 * Created Date: 10/05/2018
 * Modified Date: 10/17/2018
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
	JavascriptExecutor js;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		
		js = (JavascriptExecutor) driver;
		
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
	public void TC_02() throws Exception {
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
		
		//Handle_JqueryUI
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		sltDrop("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li[@class='ui-menu-item']", "Other");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text' and text()='Other']")).isDisplayed());
	
		
		//Handle_Angular
		driver.get("https://material.angular.io/components/select/examples");

		sltDrop("//mat-select[@placeholder='State']", "//mat-option//span", "Nevada");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Nevada']")).isDisplayed());
	
		
		//Handle_KendoUI
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		
		sltDrop("//input[@id='color']/preceding-sibling::span", "//ul[@id='color_listbox']/li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Grey']")).isDisplayed());
	
		
		//Handle_Vue
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		sltDrop("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third')]")).isDisplayed());
	}
	
	@Test(enabled = true)
	public void TC_03_Jquery_Editable_Select() throws Exception {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		edtDropDown("//div[@id='default-place']/input","//div[@id='default-place']//li","m", "BMW");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='" + "BMW" + "']")).isDisplayed());
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//( ._.')----------------------------------------------------
	public void sltDrop(String parentLocator, String allItemsLocator, String expectedValue) throws Exception  {
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
			if (allItemsDropDown.get(i).getText().trim().equals(expectedValue)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItemsDropDown.get(i));
				Thread.sleep(1000);
				
				allItemsDropDown.get(i).click();
				break;
			}
		}
		Thread.sleep(1000);
	}
	
	public void edtDropDown(String parentLocator, String allItems, String search, String expectedValue) throws Exception{
		WebElement parent = driver.findElement(By.xpath(parentLocator));
		js.executeScript("arguments[0].scrollIntoView(true);",parent);
		
		parent.sendKeys(search);
		Thread.sleep(1000);
		List <WebElement> allItemsDropDown = driver.findElements(By.xpath(allItems));
		
		int numberItem = allItemsDropDown.size(); 

		for(int i=0;i<numberItem;i++)
		{
			if(allItemsDropDown.get(i).getText().equals(expectedValue))
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",allItemsDropDown.get(i) );
				allItemsDropDown.get(i).click();
				break;
			}
		}
		Thread.sleep(1000);
		
		js.executeScript("window.scrollTo(0,-document.body.scrollHeight);");
		Thread.sleep(1000);
		
		js.executeScript("arguments[0].scrollIntoView(true);",parent);
		parent.click();
	}
}
