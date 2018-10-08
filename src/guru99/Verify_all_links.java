/* Author: truonganhdung
 * Created Date: 10/08/2018
 * Modified Date: xx/xx/2018
 * */

package guru99;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Verify_all_links {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/accessing-links-tables-selenium-webdriver.html
	@Test(enabled=true)
	public void TC_() throws Exception {
		String underConsTitle = "Under Construction: Mercury Tours";
		int i =0;
		
		driver.get("http://demo.guru99.com/test/newtours/");
		
		List<WebElement> linkElements = driver.findElements(By.tagName("a"));							
        String[] linkTexts = new String[linkElements.size()];		

		//extract the link texts of each link element		
		for (WebElement e : linkElements) {							
			linkTexts[i] = e.getText();							
			i++;			
        }		

		//test each link		
		for (String t : linkTexts) {							
			driver.findElement(By.linkText(t)).click();					
			
			if (driver.getTitle().equals(underConsTitle)) {							
	            System.out.println("\"" + t + "\"" + " is under construction.");			
	        } else {			
	            System.out.println("\"" + t + "\"" + " is working.");			
	        }	
		driver.navigate().back();			
        }
    }
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
