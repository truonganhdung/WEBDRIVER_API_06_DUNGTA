/* Author: truonganhdung
 * Created Date: 10/07/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Button_RadioButton_Checkbox_Alert {
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
	public void TC_01() throws Exception {
		driver.get("http://live.guru99.com/");
		
		clickEltByJavascript(driver.findElement(By.xpath("//a[text()='My Account']")));
		Thread.sleep(1000);
		verfPage("//form[@id='login-form']", "http://live.guru99.com/index.php/customer/account/login/");
		
		clickEltByJavascript(driver.findElement(By.xpath("//a[@title='Create an Account']")));
		Thread.sleep(1000);
		verfPage("//h1[text()='Create an Account']", "http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	@Test(enabled=false)
	public void TC_02() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		WebElement chbx = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		clickEltByJavascript(chbx);
		Thread.sleep(1000);
		verfChckBx_RadioBx(chbx);
		
		if (chbx.isSelected()) {
			clickEltByJavascript(chbx);
		}
		Thread.sleep(1000);
		assert !chbx.isSelected();
	}

	@Test(enabled=false)
	public void TC_03() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		WebElement radio = driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input"));
		clickEltByJavascript(radio);
		Thread.sleep(1000);
		
		if (!radio.isSelected()) {
			clickEltByJavascript(radio);
		}
		verfChckBx_RadioBx(radio);
	}
	
	@Test(enabled=false)
	public void TC_04_05_06() throws Exception {
		String prompt = "abc";
		driver.get("http://daominhdam.890m.com/#");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert alert04 = driver.switchTo().alert();
		Assert.assertEquals(alert04.getText(), "I am a JS Alert");
		Thread.sleep(1000);
		alert04.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert alert05 = driver.switchTo().alert();
		Assert.assertEquals(alert05.getText(), "I am a JS Confirm");
		Thread.sleep(1000);
		alert05.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alert06 = driver.switchTo().alert();
		Assert.assertEquals(alert06.getText(), "I am a JS prompt");
		Thread.sleep(1000);
		alert06.sendKeys(prompt);
		alert06.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You entered: " + prompt + "']")).isDisplayed());
	}
	
	@Test(enabled=true)
	public void TC_07() throws Exception {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	//( ._.')----------------------------------------------------
	public void clickEltByJavascript(WebElement element) {
		JavascriptExecutor jre = (JavascriptExecutor) driver;
		jre.executeScript("arguments[0].click();", element);
	}
	
	public void verfPage(String XPath, String urlPath) {
		WebElement element = driver.findElement(By.xpath(XPath));
		Assert.assertTrue(element.isDisplayed());
		
		Assert.assertEquals(driver.getCurrentUrl(), urlPath);
	}
	
	/*public boolean verfChckBx_RadioBx(WebElement element) {
		return element.isSelected();
	}*/
	
	public void verfChckBx_RadioBx(WebElement element) {
		Assert.assertTrue(element.isSelected());
	}
}
