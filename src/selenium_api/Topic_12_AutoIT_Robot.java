/* Author: truonganhdung
 * Created Date: 10/29/2018
 * Modified Date: ../../2018
 * */

package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class Topic_12_AutoIT_Robot {
    WebDriver driver;
   
    String root = System.getProperty("user.dir");
    String name = "1.jpg";
    
    String path = root + "\\upload\\" + name;
	

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
//		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_02_AutoIT() throws Exception{
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		String broswerName = driver.toString().toLowerCase();
		
		if(broswerName.contains("chrome")) {
			driver.findElement(By.cssSelector(".fileinput-button")).click();
		} else if(broswerName.contains("firefox")) {
			clickToElementByJS("//input[@type='file']");			
		}
		Thread.sleep(2000);
		
		if(broswerName.contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { ".\\upload\\chrome.exe", path });
		} else if(broswerName.contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { ".\\upload\\firefox.exe", path });
		}
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name + "']")).isDisplayed());
	}
	
	@Test(enabled=true)
	public void TC_03_Robot() throws Exception{
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		String broswerName = driver.toString().toLowerCase();
		
		StringSelection select = new StringSelection("D:\\0nlineAutoTesting\\Project\\WebDriver API\\WEBDRIVER_API_06_DUNGTA\\upload\\1.jpg");

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		if(broswerName.contains("chrome")) {
			driver.findElement(By.cssSelector(".fileinput-button")).click();
		} else if(broswerName.contains("firefox")) {
			clickToElementByJS("//input[@type='file']");			
		}
		Thread.sleep(2000);

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + name + "']")).isDisplayed());
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public Object clickToElementByJS(String locator) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.xpath(locator));
            return js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
