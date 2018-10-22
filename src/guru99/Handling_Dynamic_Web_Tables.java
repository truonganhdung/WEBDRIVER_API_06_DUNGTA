/* Author: truonganhdung
 * Created Date: 10/22/2018
 * Modified Date: xx/xx/2018
 * */

package guru99;

import java.text.NumberFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Handling_Dynamic_Web_Tables {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/handling-dynamic-selenium-webdriver.html
	@Test(enabled=true)
	public void TC_01() throws Exception{
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa");			
		
		//( ._.')--Fetch number of rows and columns from Dynamic WebTable
		//No.of Columns
		List  col = driver.findElements(By.xpath("//div[@id='leftcontainer']//th"));
		System.out.println("Total No of columns are : " +col.size());
		//No.of rows 
		List  rows = driver.findElements(By.xpath("//table[@class='dataTable']//td[a]")); 
		System.out.println("Total No of rows are : " + rows.size());
		
		//( ._.')--Fetch cell value of a particular row and column of the Dynamic Table
		WebElement baseTable = driver.findElement(By.tagName("table"));
		  
		//To find third row of table
		WebElement tableRow = baseTable.findElement(By.xpath("//div[@id='leftcontainer']//tr[3]"));
		String rowtext = tableRow.getText();
		System.out.println("Third row of table : "+rowtext);
		    
		//to get 3rd row's 2nd column data
		WebElement cellIneed = tableRow.findElement(By.xpath("//div[@id='leftcontainer']//tr[3]/td[2]"));
		String valueIneed = cellIneed.getText();
		System.out.println("Cell value is : " + valueIneed);
		
		//( ._.')--Get Maximum of all the Values in a Column of Dynamic Table
		String max;
		double m=0,r=0;

		for (int i =1; i<rows.size(); i++){    
			max= driver.findElement(By.xpath("//div[@id='leftcontainer']//tr[" + (i+1)+ "]/td[4]")).getText();
			NumberFormat f = NumberFormat.getNumberInstance(); 
			Number num = f.parse(max);
			max = num.toString();
			m = Double.parseDouble(max);
			if(m>r){    
				r=m;
			}
		}
		System.out.println("Maximum current price is : "+ r);
	}
	
	@Test(enabled=true)
	public void TC_02(){
		driver.get("http://demo.guru99.com/test/table.html");			
		
		WebElement mytable = driver.findElement(By.xpath("//tbody"));
		
    	//To locate rows of table. 
    	List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
    	//To calculate no of rows In table.
    	int rows_count = rows_table.size();
    	
    	//Loop will execute till the last row of table.
    	for (int row = 0; row < rows_count; row++) {
    	    //To locate columns(cells) of that specific row.
    	    List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
    	    
    	    //To calculate no of columns (cells). In that specific row.
    	    int columns_count = Columns_row.size();
    	    System.out.println("Number of cells In Row " + row + " are " + columns_count);
    	    
    	    //Loop will execute till the last cell of that specific row.
    	    for (int column = 0; column < columns_count; column++) {
    	        // To retrieve text from that specific cell.
    	        String celtext = Columns_row.get(column).getText();
    	        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
    	    }
    	    System.out.println("-------------------------------------------------- ");
    	}
	}
	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
