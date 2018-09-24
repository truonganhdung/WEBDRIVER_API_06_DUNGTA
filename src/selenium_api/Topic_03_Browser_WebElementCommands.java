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

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}
	
	//--
	
	//Topic 02: https://www.evernote.com/shard/s415/sh/e045f885-d0a8-41f6-83c1-39637fba4ba3/bcc608fc47462827
	@Test(enabled=false)
	public void TC_02_1_VerifyURLandTitle() {
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[@class='button']")).click();
		
		String createPageURL=  driver.getCurrentUrl();

		driver.get(loginPageUrl);
		
		Assert.assertEquals(loginPageUrl, "http://live.guru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@class='button']")).click();
		Assert.assertEquals(createPageURL, "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test(enabled=false)
	public void TC_02_2_LoginEmpty() {
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		
		String strEmailAddress = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(strEmailAddress, "This is a required field.");
		
		String strPass = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(strPass, "This is a required field.");
	}
	
	@Test(enabled=false)
	public void TC_02_3_LoginWithEmailInvalid() {
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		WebElement eleEmail = driver.findElement(By.id("email"));
		eleEmail.click();
		eleEmail.clear();
		eleEmail.sendKeys("123434234@12312.123123");
		
		driver.findElement(By.id("send2")).click();
		
		String strEmailInvalid = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(strEmailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test(enabled=false)
	public void TC_02_4_LoginWithPasswordInvalid() {
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		WebElement eleEmail = driver.findElement(By.id("email"));
		//eleEmail.click();
		eleEmail.clear();
		eleEmail.sendKeys("automation@gmail.com");
		
		WebElement elePass = driver.findElement(By.id("pass"));
		elePass.clear();
		elePass.sendKeys("123");
		
		driver.findElement(By.id("send2")).click();
		
		String strPassInvalid = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(strPassInvalid, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test(enabled=false)
	public void TC_02_5_CreateAnAccount() {
		emailRandom = randomInt();
		
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='firstname']")).clear();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("De");
		
		driver.findElement(By.xpath("//input[@id='lastname']")).clear();
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Mo");
		
		driver.findElement(By.xpath("//input[@id='email_address']")).clear();
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("automation" + emailRandom + "@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Demo@123");
		
		driver.findElement(By.xpath("//input[@id='confirmation']")).clear();
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Demo@123");
		
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		WebElement eleRegistering = driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']"));
		String strRegistering = eleRegistering.getText();
		Assert.assertEquals(strRegistering, "Thank you for registering with Main Website Store.");
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
				
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String checkLogout = driver.getTitle();
		Assert.assertEquals(checkLogout, "Magento Commerce");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String checkNavigateToHomePage = driver.getTitle();
		Assert.assertEquals(checkNavigateToHomePage, "Home page");
		
		//Don't match expected [Home page], always fail because of founding [Magento Commerce] although has been wait as above
		
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

	@Test(enabled=false)
	public void TC_03_2_VerifyElementEnable() {
		driver.get("http://daominhdam.890m.com/");
		
		WebElement txtEmail = driver.findElement(By.id("mail"));
		WebElement radAgeUnder18 = driver.findElement(By.id("under_18"));
		WebElement txtEreaEdu = driver.findElement(By.id("edu"));
		WebElement drpJob1 = driver.findElement(By.id("job1"));
		WebElement chbxDevelopment = driver.findElement(By.id("development"));
		WebElement slider1 = driver.findElement(By.id("slider-1"));
		WebElement btnEnabled = driver.findElement(By.id("button-enabled"));
		
		elementEnable(txtEmail);
		elementEnable(radAgeUnder18);
		elementEnable(txtEreaEdu);
		elementEnable(drpJob1);
		elementEnable(chbxDevelopment);
		elementEnable(slider1);
		elementEnable(btnEnabled);
		//--
		
		WebElement txtPass = driver.findElement(By.id("password"));
		WebElement radDisable = driver.findElement(By.xpath("//label[@class='light']/preceding-sibling::input[@id='radio-disabled']"));
		WebElement txtEreaBio = driver.findElement(By.xpath("//label[@for='bio']/following-sibling::textarea"));
		WebElement drpJob2 = driver.findElement(By.xpath("//label[@for='job2']/following-sibling::select[@id='job2']"));
		WebElement chbxDisabled = driver.findElement(By.xpath("//label[@class='light']/preceding-sibling::input[@id='check-disbaled']"));
		WebElement slider2 = driver.findElement(By.xpath("//label[@for='slider-2']/following-sibling::input"));
		WebElement btnDisabled = driver.findElement(By.xpath("//button[@id='button-enabled']/preceding-sibling::button"));
		
		elementEnable(txtPass);
		elementEnable(radDisable);
		elementEnable(txtEreaBio);
		elementEnable(drpJob2);
		elementEnable(chbxDisabled);
		elementEnable(slider2);
		elementEnable(btnDisabled);
	}
	
	@Test(enabled=false)
	public void TC_03_3_VerifyElementIsSelected() {
		driver.get("http://daominhdam.890m.com/");
		
		WebElement radAgeUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement chbxDevelopment = driver.findElement(By.xpath("//input[@id='development']"));
		
		radAgeUnder18.click();
		
		elementIsSelected(radAgeUnder18);
		elementIsSelected(chbxDevelopment);
	}
	//--
		
	public int randomInt() {
		Random rand = new Random();
		int random = rand.nextInt(99999) + 1;
		return random;
	}
		
	public void elementEnable(WebElement element) {
		if(element.isEnabled()) {
			System.out.println("Element is enabled");
		}
		else
			System.out.println("Element is disable");
	}
	
	public void elementIsSelected(WebElement element) {
		if(element.isSelected()) {
			System.out.println("Element is selected");
		}
		else
			element.click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
