/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Java_05_Loop_Run_ChromeHeadless {
    WebDriver driver;

    List<WebElement> elements;
    
	@BeforeClass
	public void beforeClass() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1366x768");
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		
		driver = new ChromeDriver(options);
		
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test()
	public void TC_() {
		elements = driver.findElements(By.xpath("//a"));
		
		int number = elements.size();
		System.out.println("All links = " + number);
		System.out.println("-----------------------------------------------------------------------------");
		
		/* for--------------------------------------------*/
		System.out.println("*for loop:");
		for (int i = 0 ; i < number; i++) {
			System.out.println("Links for loop = [" + i + "] = " + elements.get(i).getAttribute("href"));
		}
		System.out.println("-----------------------------------------------------------------------------");
		
		/* for-each----------------------------------------*/
		System.out.println("*for each:");
		int i = 0;
		for (WebElement e: elements) {
			System.out.println("Links for each = [" + i + "] = " + e.getAttribute("href"));
			i = i + 1;
		}
		System.out.println("-----------------------------------------------------------------------------");
		
		/* while--------------------------------------------*/
		System.out.println("*while:");
		i = 0;
		while (i < number) {
			System.out.println("Links for loop = [" + i + "] = " + elements.get(i).getAttribute("href"));
			i++;
		}
		System.out.println("-----------------------------------------------------------------------------");
		
		/* do-while--------------------------------------------*/
		System.out.println("*do while:");
		i = 0;
		do {
			System.out.println("Links for loop = [" + i + "] = " + elements.get(i).getAttribute("href"));
			i++;
		} while (i < number);
		System.out.println("-----------------------------------------------------------------------------");
	}

	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
