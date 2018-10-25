/* Author: truonganhdung
 * Created Date: 10/22/2018
 * Modified Date: xx/xx/2018
 * */

package guru99;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class II_X_Select_Date_from_DatePicker {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\0nlineAutoTesting\\Software\\chromedriver-2.41.exe");
		driver = new ChromeDriver();
        
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/handling-date-time-picker-using-selenium.html
	@Test(enabled=false)
	public void TC_DatePicker() {
		driver.get("http://demo.guru99.com/test/");
		
		WebElement dateBox = driver.findElement(By.xpath("//input[@name='bdaytime']"));
		dateBox.sendKeys("09252013");
		dateBox.sendKeys(Keys.TAB);
		dateBox.sendKeys("0245PM");
	}
	
	@Test(enabled=true)
    public void testDatePicker() throws Exception{
        String dateTime ="12/07/2015 1:00 AM";
        
        //Split the date time to get only the date part
        String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

        //get the year difference between current year and year to set in calander
        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);

        //--| ._.|'
        driver.get("http://demos.telerik.com/kendo-ui/datetimepicker/index");
        
        //Open calendar
        WebElement selectDate = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));
	    selectDate.click();
	    Thread.sleep(1000);
	    
	    //button to move next in calendar
	    WebElement nextLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//a[contains(@class,'k-nav-next')]"));
	    //button to click in center of calendar header
	    WebElement midLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//a[contains(@class,'k-nav-fast')]"));
	    //button to move previous month in calendar
	    WebElement previousLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//a[contains(@class,'k-nav-prev')]")); 

        midLink.click();

        Thread.sleep(1000);
        if(yearDiff!=0){
            //if you have to move next year
            if(yearDiff>0){
                for(int i =0; i< yearDiff; i++){
                   nextLink.click();
                }
            }

            //if you have to move previous year
            else if(yearDiff<0){
                for(int i=0; i< (yearDiff*(-1)); i++){
                    previousLink.click();
                }
            }
        }

        //Get all months from calendar to select correct one
        List<WebElement> list_AllMonth = driver.findElements(By.xpath("//td[not(contains(@class,'k-other-month'))]"));
        list_AllMonth.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();
        Thread.sleep(1000);
        
        //get all dates from calendar to select correct one
        //List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
        List<WebElement> list_AllDate = driver.findElements(By.xpath("//td[not(contains(@class,'k-other-month'))]"));
        list_AllDate.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();
        Thread.sleep(1000);
        
        ///FOR TIME
        WebElement selectTime = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));
        selectTime.click();

        //get list of times
        List<WebElement> allTime = driver.findElements(By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//li[@role='option']"));
      
        dateTime = dateTime.split(" ")[1] + " " + dateTime.split(" ")[2];

    	//select correct time
        for (WebElement webElement : allTime) {
            if(webElement.getText().equalsIgnoreCase(dateTime))
                webElement.click();
        }
    }
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
