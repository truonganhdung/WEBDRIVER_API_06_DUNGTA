package guru99.II_VII_POM.simplePOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Guru99Login_SimplePOM {

	WebDriver driver;
	By user99GuruName = By.name("uid");
	By password99Guru = By.name("password");
	By titleText =By.className("barone");
	By login = By.name("btnLogin");
	
	public Guru99Login_SimplePOM(WebDriver driver){
		this.driver = driver;
	}

	public void setUserName(String strUserName){
		driver.findElement(user99GuruName).sendKeys(strUserName);;
	}
	
	public void setPassword(String strPassword){
		 driver.findElement(password99Guru).sendKeys(strPassword);
	}
	
	public void clickLogin(){
			driver.findElement(login).click();
	}

	public String getLoginTitle(){
	 return	driver.findElement(titleText).getText();
	}
	
	/**
	 * This POM method will be exposed in test case to login in the application
	 * @param strUserName
	 * @param strPasword
	 * @return
	 */
	public void loginToGuru99(String strUserName,String strPasword){
		this.setUserName(strUserName);
		this.setPassword(strPasword);
		this.clickLogin();		
	}
}
