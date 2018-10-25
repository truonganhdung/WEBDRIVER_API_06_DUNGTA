package guru99.II_VII_POM.simplePOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Guru99HomePage_SimplePOM {

	WebDriver driver;
	By homePageUserName = By.xpath("//table//tr[@class='heading3']");
	
	public Guru99HomePage_SimplePOM(WebDriver driver){
		this.driver = driver;
	}
	
	public String getHomePageDashboardUserName(){
		 return	driver.findElement(homePageUserName).getText();
	}
}