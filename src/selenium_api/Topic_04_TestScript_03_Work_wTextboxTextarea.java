package selenium_api;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;

public class Topic_04_TestScript_03_Work_wTextboxTextarea {
	WebDriver driver;

	// Declare Variable
	String customerID;
	String CustomerName, Gender, DoB, Address, City, State, PIN, Mobile, Email, Password;
	String edtAddress, edtCity, edtState, edtPIN, edtMobile, edtEmail;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Data Create
		CustomerName = "Dung Truong";
		Gender = "male";
		DoB = "1987-03-12";
		Address = "123 VVT";
		City = "HCMC";
		State = "district";
		PIN = "600000";
		Mobile = "01268734611";
		Email = "autotest" + random() + "@amil.com";
		Password = "11234";

		// Data Edited
		edtAddress = "edit456 VVT";
		edtCity = "editHCMC";
		edtState = "editdistrict";
		edtPIN = "800000";
		edtMobile = "01268734000";
		edtEmail = "editautotest" + random() + "@amil.com";
	}

	@Test
	public void TC() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr155533");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("aqAtAda");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// Verify Home page display
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		// Add New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input data
		iptData(driver.findElement(By.xpath("//input[@name='name']")), CustomerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		iptData(driver.findElement(By.xpath("//input[@name='dob']")), DoB);
		iptData(driver.findElement(By.xpath("//textarea[@name='addr']")), Address);
		iptData(driver.findElement(By.xpath("//input[@name='city']")), City);
		iptData(driver.findElement(By.xpath("//input[@name='state']")), State);
		iptData(driver.findElement(By.xpath("//input[@name='pinno']")), PIN);
		iptData(driver.findElement(By.xpath("//input[@name='telephoneno']")), Mobile);
		iptData(driver.findElement(By.xpath("//input[@name='emailid']")), Email);
		iptData(driver.findElement(By.xpath("//input[@name='password']")), Password);
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		// Verify after creating new customer
		vefData(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")), CustomerName);
		vefData(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")), Gender);
		vefData(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")), DoB);
		vefData(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")), Address);
		vefData(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")), City);
		vefData(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")), State);
		vefData(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")), PIN);
		vefData(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), Mobile);
		vefData(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")), Email);
		
		// Edit Customer
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer']")).isDisplayed());

		// Verify data at edit customer screen
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), CustomerName);
		vefData(driver.findElement(By.xpath("//textarea[@name='addr']")), Address);
		
		// update new data
		iptData(driver.findElement(By.xpath("//textarea[@name='addr']")), edtAddress);
		iptData(driver.findElement(By.xpath("//input[@name='city']")), edtCity);
		iptData(driver.findElement(By.xpath("//input[@name='state']")), edtState);
		iptData(driver.findElement(By.xpath("//input[@name='pinno']")), edtPIN);
		iptData(driver.findElement(By.xpath("//input[@name='telephoneno']")), edtMobile);
		iptData(driver.findElement(By.xpath("//input[@name='emailid']")), edtEmail);
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		// Verify after update new data
		vefData(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")), edtAddress);
		vefData(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")), edtCity);
		vefData(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")), edtState);
		vefData(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")), edtPIN);
		vefData(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), edtMobile);
		vefData(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")), edtEmail);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int random() {
		Random rand = new Random();
		int email = rand.nextInt(5000) + 1;
		return email;
	}
	
	public void iptData(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public void vefData(WebElement element, String value) {
		Assert.assertEquals(element.getText(), value);
	}

}
