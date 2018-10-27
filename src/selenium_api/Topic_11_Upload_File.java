/* Author: truonganhdung
 * Created Date: xx/xx/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Upload_File {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_() throws Exception {
		String path = System.getProperty("user.dir");
		String name1 = 
		System.out.println(root);
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//driver.findElement(By.xpath("//input[@type='file']")).sendKeys(arg0);
		
		//Thread.sleep(2000);
	}

	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
