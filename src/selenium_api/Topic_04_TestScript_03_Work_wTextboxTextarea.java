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
	
	WebElement eltCustomerName;
	WebElement eltDoB;
	WebElement eltAddress;
	WebElement eltCity;
	WebElement eltState;
	WebElement eltPIN;
	WebElement eltMobile;
	WebElement eltEmail;
	WebElement eltPassword;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Data Create
		CustomerName = "Dung Truong";
		Gender = "male";
		DoB = "01/01/1987";
		Address = "123 VVT";
		City = "HCMC";
		State = "district";
		PIN = "600000";
		Mobile = "01268734611";
		Email = "autotest" + random() + "@amil.com";
		Password = "11234";
		
		// Data Edited
		edtAddress = "edit123 VVT";
		edtCity = "editHCMC";
		edtState = "editdistrict";
		edtPIN = "800000";
		edtMobile = "01268734000";
		edtEmail = "editautotest" + random() + "@amil.com";
	}

	@Test
	public void TC_AddNewCustomer() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr155533");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("aqAtAda");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// Verify Home page display
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Find Element
		eltCustomerName = driver.findElement(By.xpath("//input[@name='name']"));
		eltDoB = driver.findElement(By.xpath("//input[@name='dob']"));
		eltAddress = driver.findElement(By.xpath("//textarea[@name='addr']"));
		eltCity = driver.findElement(By.xpath("//input[@name='city']"));
		eltState = driver.findElement(By.xpath("//input[@name='state']"));
		eltPIN = driver.findElement(By.xpath("//input[@name='pinno']"));
		eltMobile = driver.findElement(By.xpath("//input[@name='telephoneno']"));
		eltEmail = driver.findElement(By.xpath("//input[@name='emailid']"));
		eltPassword = driver.findElement(By.xpath("//input[@name='password']"));

		// Input data
		inputData(eltCustomerName, CustomerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		inputData(eltDoB, DoB);
		inputData(eltAddress, Address);
		inputData(eltCity, City);
		inputData(eltState, State);
		inputData(eltPIN, PIN);
		inputData(eltMobile, Mobile);
		inputData(eltEmail, Email);
		inputData(eltPassword, Password);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
		//Verify after creating new customer
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CustomerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), "1987-01-01");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), PIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), Mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), Email);
	}

	@Test
	public void TC_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer']")).isDisplayed());
		
		//Verify data at edit customer screen
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), CustomerName);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), Address);
		
		//update new data
		inputData(eltAddress, edtAddress);
		inputData(eltCity, edtCity);
		inputData(eltState, edtState);
		inputData(eltPIN, edtPIN);
		inputData(eltMobile, edtMobile);
		inputData(eltEmail, edtEmail);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
	
		//Verify after update new data
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), PIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), Mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), Email);
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

	public void inputData(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

}
