/* Author: truonganhdung
 * Created Date: xx/xx/2018
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_0_Template2 {
    WebDriver driver;
    WebDriverWait wait;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	
	//( ._.')----------------------------------------------------
	@Test
	public void clickAllItemInDropDown () throws Exception{
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
	
		WebElement parent = driver.findElement(By.xpath("//div[@id='default-place']/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",parent);
		
		parent.sendKeys("o");;
		Thread.sleep(1000);
		
		List <WebElement> allItemsDropDown = driver.findElements(By.xpath("//div[@id='default-place']//li"));
		
		int numberItem = allItemsDropDown.size(); 

		for(int i=0;i<numberItem;i++)
		{
			if(allItemsDropDown.get(i).getText().equals("Volvo"))
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",allItemsDropDown.get(i) );
				allItemsDropDown.get(i).click();
				break;
			}
		}
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",parent);
		
		String result = driver.findElement(By.xpath("//li[@class='es-visible selected']")).getText();
		System.out.print(result);
		//Assert.assertEquals(result, "Volvo");
	}
	
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
