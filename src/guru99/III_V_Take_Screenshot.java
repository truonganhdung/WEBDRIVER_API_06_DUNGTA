/**
 * Author: truonganhdung
 * Created Date: 10/24/2018
 * Modified Date: ../../2018
 */

package guru99;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

public class III_V_Take_Screenshot {
	WebDriver driver ;

/*	@Test
    public void testGuru99TakeScreenShot() throws Exception{
		
    	//System.setProperty("webdriver.firefox.marionette","\\geckodriver.exe");
    	driver = new FirefoxDriver();

        driver.get("http://demo.guru99.com/V4/");

        Thread.sleep(2000);
        this.takeSnapShot(driver, "\\screenshot\\test.png") ;
    }
     
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
    	TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile = new File(fileWithPath);

        FileUtils.copyFile(SrcFile, DestFile);
    }
*/
	@Test
    public void TestJavaS1(){
    	driver=new FirefoxDriver();

    	driver.manage().window().maximize();

    	driver.get("http://www.google.com");

    	File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
    		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\screenshot\\error.png"));
    	}
    	catch (IOException e){
    		System.out.println(e.getMessage());
    	}
    }
    
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}