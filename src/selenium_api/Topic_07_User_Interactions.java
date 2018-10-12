/* Author: truonganhdung
 * Created Date: 10/12/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_User_Interactions {
    WebDriver driver;
    Actions action;
    
    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=false)
	public void TS_01_TC_01() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		WebElement elt = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		action.moveToElement(elt).perform();
		
		Assert.assertEquals(elt.getAttribute("title"),"Hooray!");
	}
	
	@Test(enabled=false)
	public void TS_01_TC_02() throws Exception {
		driver.get("https://www.myntra.com/");
		
		WebElement eltIcon = driver.findElement(By.xpath("//span[contains(@class,'desktop-iconUser')]"));
		action.moveToElement(eltIcon).perform();
		Thread.sleep(1000);
		
		WebElement eltLogin = driver.findElement(By.xpath("//a[text()='login']"));
		action.moveToElement(eltLogin).click().perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Login to Myntra']")).isDisplayed());
	}

	@Test(enabled=false)
	public void TS_02_Click_hold_Element_Select_Multiple_Item() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(3)).release().perform();
		Thread.sleep(1000);
		
		List <WebElement> sltItems = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(sltItems.size(),4);
	}
	
	@Test(enabled=false)
	public void TS_02_Click_hold_Random_Element_Select_Multiple_Item() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.keyDown(Keys.LEFT_CONTROL).clickAndHold(allItems.get(randNum())).moveToElement(allItems.get(randNum())).keyUp(Keys.LEFT_CONTROL).release().perform();
		Thread.sleep(1000);
		
		WebElement slt2Item = driver.findElement(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertTrue(slt2Item.isDisplayed());
	}
	
	@Test(enabled=false)
	public void TS_03_Double_Click() throws Exception {
		driver.get("http://www.seleniumlearn.com/double-click");
		
		WebElement dClick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		
		action.doubleClick(dClick).perform();
		Thread.sleep(1000);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"The Button was double-clicked.");
		alert.accept();
	}
	
	@Test(enabled=false)
	public void TS_04_Right_Click() throws Exception {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement rClick = driver.findElement(By.xpath("//span[text()='right click me']"));
				
		action.contextClick(rClick).perform();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not(contains(@class,'context-menu-hover'))]")).isDisplayed());
		
		WebElement quit = driver.findElement(By.xpath("//span[text()='Quit']"));
		action.moveToElement(quit).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover')]")).isDisplayed());
		
		action.click(quit).perform();
	}
	
	@Test(enabled=false)
	public void TS_05_Drag_Drop_TC_01() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement drpSource = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drpTarget = driver.findElement(By.xpath("//div[@id='droptarget']"));
				
		action.dragAndDrop(drpSource, drpTarget).perform();
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
	}
	
	@Test(enabled=true)
	public void TS_05_Drag_Drop_TC_02() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		
		WebElement drpSource = driver.findElement(By.xpath("//p[text()='Drag me to my target']"));
		WebElement drpTarget = driver.findElement(By.xpath("//div/p[text()='Drop here']"));
				
		action.dragAndDrop(drpSource, drpTarget).perform();
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div/p[text()='Dropped!']")).isDisplayed());
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randNum() {
		Random rand = new Random();
		int i = rand.nextInt(9);
		return i;
	}
}
