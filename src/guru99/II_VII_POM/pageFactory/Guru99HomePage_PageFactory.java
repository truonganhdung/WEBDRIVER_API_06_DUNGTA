package guru99.II_VII_POM.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99HomePage_PageFactory{

	WebDriver driver;
	@FindBy(xpath="//table//tr[@class='heading3']")
	WebElement homePageUserName;
	
	public Guru99HomePage_PageFactory(WebDriver driver){
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public String getHomePageDashboardUserName(){
		 return	homePageUserName.getText();
	}
}
