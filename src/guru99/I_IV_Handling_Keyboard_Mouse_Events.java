/* Author: truonganhdung
 * Created Date: 10/08/2018
 * Modified Date: xx/xx/2018
 * */

package guru99;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_IV_Handling_Keyboard_Mouse_Events {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/keyboard-mouse-events-files-webdriver.html
	@Test(enabled=false)
	public void TC_Mouse() {
		driver.get("http://demo.guru99.com/test/newtours/");
		
		WebElement linkHome = driver.findElement(By.linkText("Home"));
		WebElement tdHome = driver.findElement(By.xpath("//html/body/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr"));
		
		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.moveToElement(linkHome).build();
		
		String bgColor = tdHome.getCssValue("background-color");
        System.out.println("Before hover: " + bgColor); 
        
        mouseOverHome.perform();
        bgColor = tdHome.getCssValue("background-color");
        System.out.println("After hover: " + bgColor);
	}

	@Test(enabled=true)
	public void TC_Keyboard() {
		driver.get("https://www.facebook.com/");
		
		WebElement txtEmail = driver.findElement(By.xpath("//input[@id='email']"));
		
		Actions builder = new Actions(driver);
		Action actions = builder.moveToElement(txtEmail).click().keyDown(txtEmail, Keys.SHIFT).sendKeys(txtEmail, "hello").keyUp(txtEmail, Keys.SHIFT).doubleClick(txtEmail).contextClick().build();
		
		actions.perform();
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
