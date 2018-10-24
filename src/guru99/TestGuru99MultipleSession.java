/* Author: truonganhdung
 * Created Date: 10/24/2018
 * Modified Date: ../../2018
 * */

package guru99;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestGuru99MultipleSession {
	
    @Test
    public void executSession1(){
    	WebDriver driver = new FirefoxDriver();
    	
    	driver.get("http://demo.guru99.com/V4/");
    	driver.findElement(By.name("uid")).sendKeys("Driver 1");
    	driver.quit();
    }
        
    @Test
    public void executSession2(){
    	WebDriver driver = new FirefoxDriver();
    	
    	driver.get("http://demo.guru99.com/V4/");
    	driver.findElement(By.name("uid")).sendKeys("Driver 2");
    	driver.quit();
    }
    
    @Test
    public void executSession3(){
    	WebDriver driver = new FirefoxDriver();
    	
    	driver.get("http://demo.guru99.com/V4/");
    	driver.findElement(By.name("uid")).sendKeys("Driver 3");
    	driver.quit();
    }
}