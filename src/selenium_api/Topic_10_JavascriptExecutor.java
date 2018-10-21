/* Author: truonganhdung
 * Created Date: 10/19/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_JavascriptExecutor {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=false)
	public void TC_01_JavascriptExcecutor() {
		driver.get("http://live.guru99.com/");
		
		//Step 02: Get domain
		String domain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domain, "live.guru99.com");
		
		//Step 03: Get URL
		String urlPage = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(urlPage, driver.getCurrentUrl());
		
		//Step 04: Open MOBILE page
		WebElement element = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(element);
		clickToElementByJS(element);
		
		//Step 05: Add SAMSUNG GALAXY to Cart 
		WebElement cartSS = driver.findElement(By.xpath("//div[h2[a[text()='Samsung Galaxy']]]//span[text()='Add to Cart']"));
		clickToElementByJS(cartSS);
		
		//Step 06: Verify "Samsung Galaxy was added to your shopping cart."
		String addSucceed = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(addSucceed.contains("Samsung Galaxy was added to your shopping cart."));
		/*
		String addSucceed = driver.findElement(By.xpath("//span[text()='Samsung Galaxy was added to your shopping cart.']")).getText();
		String sText = js.executeScript("return document.documentElement.innerText;").toString();
		String[] arrtemp = sText.split("[\\r\\n]+");
		for(String a: arrtemp) {
			if(a.equals(addSucceed)) {
				System.out.println("Found");
				break;
			}
		}
		*/
		
		//Step 07: Open PRIVACY POLICY page
		WebElement pPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		clickToElementByJS(pPolicy);
		String sTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(sTitle, driver.getTitle());
		
		//Step 08-09: Scroll to bottom page
		scrollToBottomPage();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='The number of items in your Wishlist.']")).isDisplayed());
		
		//Step 10: navigate to new domain
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String domainNavigate = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainNavigate, "demo.guru99.com");
	}

	@Test(enabled=true)
	public void TC_02_RemoveAttribute() {
		String fname = "Auto", lname = "Test";
		
		navigateToUrlByJS("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		
		WebElement IFrame = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(IFrame);
		
		//Step 02: Remove disabled
		WebElement lName = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(lName,"disabled");
		
		//Step 03: Send key to First + Last name
		WebElement fName = driver.findElement(By.xpath("//input[@name='fname']"));
		sendkeyToElementByJS(fName, fname);
		sendkeyToElementByJS(lName, lname);
		
		//Step 04: Press [Submit]
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		//Step 05:
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'fname=" + fname + "&lname=" + lname + "')]")).isDisplayed());
	}
	
	@Test(enabled=false)
	public void TC_03() throws Exception {
		int emailRandom = randomInt();

		navigateToUrlByJS("http://live.guru99.com");
		
		clickToElementByJS(driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")));
		clickToElementByJS(driver.findElement(By.xpath("//span[text()='Create an Account']")));
		
		WebElement eltFirstName = driver.findElement(By.xpath("//input[@id='firstname']"));
		WebElement eltLastName = driver.findElement(By.xpath("//input[@id='lastname']"));
		WebElement eltEmail = driver.findElement(By.xpath("//input[@id='email_address']"));
		WebElement eltPass = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement eltConfirm = driver.findElement(By.xpath("//input[@id='confirmation']"));
		
		sendkeyToElementByJS(eltFirstName, "De");
		sendkeyToElementByJS(eltLastName, "Mo");
		sendkeyToElementByJS(eltEmail, "automation" + emailRandom + "@gmail.com");
		sendkeyToElementByJS(eltPass, "Demo@123");
		sendkeyToElementByJS(eltConfirm, "Demo@123");
		
		clickToElementByJS(driver.findElement(By.xpath("//span[text()='Register']")));
			
		String strRegistering = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(strRegistering.contains("Thank you for registering with Main Website Store."));
		
		clickToElementByJS(driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[text()='Account']")));
		clickToElementByJS(driver.findElement(By.xpath("//a[@title='Log Out']")));
		
		Thread.sleep(10000);
		
		String checkNavigateToHomePage = (String) executeForBrowser("return document.title");
		Assert.assertEquals(checkNavigateToHomePage, "Home page");
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	//( ._.')-COMMON FUNCTION------------------------------------
	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
    }

    public Object executeForBrowser(String javaSript) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object clickToElementByJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object sendkeyToElementByJS(WebElement element, String value) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object removeAttributeInDOM(WebElement element, String attribute) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object scrollToBottomPage() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object navigateToUrlByJS(String url) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public int randomInt() {
		Random rand = new Random();
		int random = rand.nextInt(99999) + 1;
		return random;
	}
}
