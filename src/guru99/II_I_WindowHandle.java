/* Author: truonganhdung
 * Created Date: 10/22/2018
 * Modified Date: xx/xx/2018
 * */

package guru99;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class II_I_WindowHandle {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/alert-popup-handling-selenium.html
	@Test(enabled=true)
	public void TC_01() throws Exception{
		driver.get("http://demo.guru99.com/popup.php");
		String MainWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

        // To handle all new opened window
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();
        		
        while(i1.hasNext()){
            String ChildWindow=i1.next();

            if(!MainWindow.equalsIgnoreCase(ChildWindow)){
                // Switching to Child window
                driver.switchTo().window(ChildWindow);
                driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");
                
                driver.findElement(By.name("btnLogin")).click();

                //Closing the Child Window.
                driver.close();
            }
        }
        	//Switching to Parent window i.e Main Window.
            driver.switchTo().window(MainWindow);
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
