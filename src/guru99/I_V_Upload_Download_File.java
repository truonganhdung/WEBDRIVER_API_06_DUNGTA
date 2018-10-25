/* Author: truonganhdung
 * Created Date: 10/08/2018
 * Modified Date: xx/xx/2018
 * */

package guru99;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

public class I_V_Upload_Download_File {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/upload-download-file-selenium-webdriver.html
	@Test(enabled=false)
	public void TC_Upload() throws Exception {
		driver.get("http://demo.guru99.com/test/upload");
		
		WebElement uploadElt = driver.findElement(By.id("uploadfile_0"));
		
		uploadElt.sendKeys("D:\\a.txt");
		driver.findElement(By.id("terms")).click();
		
		driver.findElement(By.name("send")).click();
		Thread.sleep(1000);
	}
	
	@Test(enabled=true)
	public void TC_Download() throws Exception {
		driver.get("http://demo.guru99.com/test/yahoo.html");
		
		WebElement downloadButton = driver.findElement(By.id("messenger-download"));
        String sourceLocation = downloadButton.getAttribute("href");
        String wget_command = "cmd /c D:\\0nlineAutoTesting\\Software\\Wget\\wget.exe -P D:\\0nlineAutoTesting\\Software\\Wget --no-check-certificate " + sourceLocation;

        try {
	        Process exec = Runtime.getRuntime().exec(wget_command);
	        int exitVal = exec.waitFor();
	        System.out.println("Exit value: " + exitVal);
        } catch (InterruptedException | IOException ex) {
        	System.out.println(ex.toString());
        }
    }
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
