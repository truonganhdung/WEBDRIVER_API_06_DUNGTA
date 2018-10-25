/* Author: truonganhdung
 * Created Date: 10/25/2018
 * Modified Date: ../../2018
 * https://www.guru99.com/handling-ajax-call-selenium-webdriver.html
 * */

package guru99;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IV_III_Handling_AJAX {
	private String URL = "http://demo.guru99.com/test/ajax.html";
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(URL);
	}
	
	@Test
	public void test_AjaxExample() {
		By container = By.cssSelector(".container");
		
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(container));

		WebElement noTextElement = driver.findElement(By.className("radiobutton"));
		String textBefore = noTextElement.getText().trim();

		driver.findElement(By.id("yes")).click();

		driver.findElement(By.id("buttoncheck")).click();

		WebElement TextElement = driver.findElement(By.className("radiobutton"));
		wait.until(ExpectedConditions.visibilityOf(TextElement));
		String textAfter = TextElement.getText().trim();

		Assert.assertNotEquals(textBefore, textAfter);
		System.out.println("Ajax Call Performed");
		
		String expectedText = "Radio button is checked and it's value is Yes";

		Assert.assertEquals(textAfter, expectedText);
		driver.close();
	}
}