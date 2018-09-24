/* Author: truonganhdung
 * Created Date: 09/23/2018
 * Modified Date: 09/24/2018
 * */

package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Browser_WebElementCommands {
    WebDriver driver;
    int emailRandom;

    //-_-Precondition_-_-_-_-_
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//-_-Testcase-_-_-_-_-_-_
	
	//Topic 02: https://www.evernote.com/shard/s415/sh/e045f885-d0a8-41f6-83c1-39637fba4ba3/bcc608fc47462827
	@Test(enabled=true)
	public void TC_02_1_VerifyURLandTitle() {
		//driver.get("http://live.guru99.com");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		findElement("//div[@class='footer']//a[@title='My Account']");
				
		String loginPageUrl = driver.getCurrentUrl();

		findElement("//a[@class='button']");
	
		String createPageURL=  driver.getCurrentUrl();

		driver.get(loginPageUrl);
		
		Assert.assertEquals(loginPageUrl, "http://live.guru99.com/index.php/customer/account/login/");
		
		findElement("//a[@class='button']");
		Assert.assertEquals(createPageURL, "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test(enabled=true)
	public void TC_02_2_LoginEmpty() {
		driver.get("http://live.guru99.com");
		findElement("//div[@class='footer']//a[@title='My Account']");
			
		findElement("//span[text()='Login']");
		
		String strEmailAddress = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(strEmailAddress, "This is a required field.");
		
		String strPass = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(strPass, "This is a required field.");
	}
	
	@Test(enabled=true)
	public void TC_02_3_LoginWithEmailInvalid() {
		driver.get("http://live.guru99.com");
		findElement("//div[@class='footer']//a[@title='My Account']");
		
		WebElement eltEmail = driver.findElement(By.id("email"));
		inputData(eltEmail, "123434234@12312.123123");
		
		driver.findElement(By.id("send2")).click();
		
		String strEmailInvalid = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(strEmailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test(enabled=true)
	public void TC_02_4_LoginWithPasswordInvalid() {
		driver.get("http://live.guru99.com");
		findElement("//div[@class='footer']//a[@title='My Account']");
		
		WebElement eltEmail = driver.findElement(By.id("email"));
		inputData(eltEmail, "automation@gmail.com");
		
		WebElement eltPass = driver.findElement(By.id("pass"));
		inputData(eltPass, "123");
		
		driver.findElement(By.id("send2")).click();
		
		String strPassInvalid = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(strPassInvalid, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test(enabled=true)
	public void TC_02_5_CreateAnAccount() throws InterruptedException {
		emailRandom = randomInt();

		driver.get("http://live.guru99.com");
		findElement("//div[@class='footer']//a[@title='My Account']");
		findElement("//span[text()='Create an Account']");
		
		WebElement eltFirstName = driver.findElement(By.xpath("//input[@id='firstname']"));
		WebElement eltLastName = driver.findElement(By.xpath("//input[@id='lastname']"));
		WebElement eltEmail = driver.findElement(By.xpath("//input[@id='email_address']"));
		WebElement eltPass = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement eltConfirm = driver.findElement(By.xpath("//input[@id='confirmation']"));
		
		inputData(eltFirstName, "De");
		inputData(eltLastName, "Mo");
		inputData(eltEmail, "automation" + emailRandom + "@gmail.com");
		inputData(eltPass, "Demo@123");
		inputData(eltConfirm, "Demo@123");
		
		findElement("//span[text()='Register']");
			
		WebElement eltRegistering = driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']"));
		String strRegistering = eltRegistering.getText();
		Assert.assertEquals(strRegistering, "Thank you for registering with Main Website Store.");
		
		findElement("//a[@class='skip-link skip-account']//span[text()='Account']");
		findElement("//a[@title='Log Out']");
		
		Thread.sleep(5000);
		
		String checkNavigateToHomePage = driver.getTitle();
		Assert.assertEquals(checkNavigateToHomePage, "Home page");
		
		driver.close();
	}
	
	//Topic 03: https://www.evernote.com/shard/s415/sh/bfbceded-f40d-438c-ac15-0cdc44c3a5b6/020007bcdac4f9d6
	@Test(enabled=true)
	public void TC_03_1_VerifyElementDisplay() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement txtEmail = driver.findElement(By.id("mail"));
		if(txtEmail.isDisplayed()) {
			txtEmail.clear();
			txtEmail.sendKeys("Automation Testing");
		}
		
		WebElement radAgeUnder18 = driver.findElement(By.id("under_18"));
		if(radAgeUnder18.isDisplayed()) {
			radAgeUnder18.click();
		}
		
		WebElement txtEreaEdu = driver.findElement(By.id("edu"));
		if(txtEreaEdu.isDisplayed()) {
			txtEreaEdu.clear();
			txtEreaEdu.sendKeys("Automation Testing");
		}
	}

	@Test(enabled=true)
	public void TC_03_2_VerifyverifyEltEnable() {
		driver.get("http://daominhdam.890m.com/");
		
		verifyEltEnable(driver.findElement(By.id("mail")));
		verifyEltEnable(driver.findElement(By.id("under_18")));
		verifyEltEnable(driver.findElement(By.id("edu")));
		verifyEltEnable(driver.findElement(By.id("job1")));
		verifyEltEnable(driver.findElement(By.id("development")));
		verifyEltEnable(driver.findElement(By.id("slider-1")));
		verifyEltEnable(driver.findElement(By.id("button-enabled")));
		
		verifyEltEnable(driver.findElement(By.id("password")));
		verifyEltEnable(driver.findElement(By.xpath("//label[@class='light']/preceding-sibling::input[@id='radio-disabled']")));
		verifyEltEnable(driver.findElement(By.xpath("//label[@for='bio']/following-sibling::textarea")));
		verifyEltEnable(driver.findElement(By.xpath("//label[@for='job2']/following-sibling::select[@id='job2']")));
		verifyEltEnable(driver.findElement(By.xpath("//label[@class='light']/preceding-sibling::input[@id='check-disbaled']")));
		verifyEltEnable(driver.findElement(By.xpath("//label[@for='slider-2']/following-sibling::input")));
		verifyEltEnable(driver.findElement(By.xpath("//button[@id='button-enabled']/preceding-sibling::button")));
	}
	
	@Test(enabled=true)
	public void TC_03_3_VerifyeltIsSelected() {
		driver.get("http://daominhdam.890m.com/");
		
		WebElement radAgeUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement chbxDevelopment = driver.findElement(By.xpath("//input[@id='development']"));
		
		radAgeUnder18.click();
		
		eltIsSelected(radAgeUnder18);
		eltIsSelected(chbxDevelopment);
	}
	
	
	//-_-Function Common_-_-_-_
		
	public int randomInt() {
		Random rand = new Random();
		int random = rand.nextInt(99999) + 1;
		return random;
	}
		
	public void verifyEltEnable(WebElement element) {
		if(element.isEnabled()) {
			System.out.println("Element is enabled");
		}
		else
			System.out.println("Element is disable");
	}
	
	public void eltIsSelected(WebElement element) {
		if(element.isSelected()) {
			System.out.println("Element is selected");
		}
		else
			element.click();
	}
	
	public void inputData(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public void findElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	//-_-Clean up_-_-_-_-_-_-_
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
