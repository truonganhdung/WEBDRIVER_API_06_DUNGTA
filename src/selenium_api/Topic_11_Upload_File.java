/* Author: truonganhdung
 * Created Date: 10/27/2018
 * Modified Date: ../../2018
 * */

package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Upload_File {
    WebDriver driver;
    WebDriverWait wait;
    
    String root = System.getProperty("user.dir");
    String name1 = "1.jpg", name2 = "2.jpg", name3 = "3.jpg";
    
    String path01 = root + "\\upload\\" + name1;
    String path02 = root + "\\upload\\" + name2;
    String path03 = root + "\\upload\\" + name3;
	

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=false)
	public void TC_01(){
		String files[] = {path01, path02, path03};
		int fileLength = files.length;
		
		
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		for(int i = 0; i < fileLength; i++) {
			WebElement uploadFile= driver.findElement(By.xpath("//input[@type='file']"));
			
			uploadFile.sendKeys(files[i]);
			//uploadFile.sendKeys(path01 + "\n" + path02 + "\n" + path03);
			
		}
		List<WebElement> uploaded = driver.findElements(By.xpath("//table[@class='table table-striped']//p[@class='name']"));
		for(WebElement i: uploaded) {
			Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + i.getText() + "']")).isDisplayed());
		}
			
		List<WebElement> startButton = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for(WebElement i: startButton) {
			i.click();
		}
		List<WebElement> checkUploaded = driver.findElements(By.xpath("//p[@class='name']/a"));
		for(WebElement i: checkUploaded) {
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + i.getText() + "']")).isDisplayed());
		}
		List<WebElement> img = driver.findElements(By.xpath("//table[@class='table table-striped']//img"));
		for(WebElement i: img) {
			Assert.assertTrue(checkAnyImageLoaded(i));
		}
		
		driver.close();
	}
	
	
	@Test(enabled=true)
	public void TC_04(){
		String emailAddress = "uploadFolder@gmail.com", firstName = "uploadFolderFName", uploadFolder = "uploadFolder";
		
		
		driver.get("https://encodable.com/uploaddemo/");
		
		driver.findElement(By.xpath("//input[@id='uploadname1']")).sendKeys(path01);
		driver.findElement(By.xpath("//a[text()='[remove this file]']")).click();
		
		
		Select uploadTo = new Select(driver.findElement(By.xpath("//select[@name='subdir1']")));
		uploadTo.selectByValue("/");
		
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(uploadFolder);
		
		driver.findElement(By.id("formfield-email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("formfield-first_name")).sendKeys(firstName);
		
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//dt[contains(text(),'Your upload is complete')]")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dd[contains(text(),'Email Address')]")).getText(), "Email Address: " + emailAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dd[contains(text(),'First Name')]")).getText(), "First Name: " + firstName);
		Assert.assertTrue(driver.findElement(By.xpath("//dt/a[text()='" + name1 + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		
		driver.findElement(By.xpath("//a[text()='" + uploadFolder + "']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + name1 + "']")).isDisplayed());
		
		driver.close();
	}

	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public boolean checkAnyImageLoaded(WebElement image) {
		  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		  return (boolean) jsExecutor.executeScript("return arguments[0].complete && " + "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
	}
}
