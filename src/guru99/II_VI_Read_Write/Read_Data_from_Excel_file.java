/* Author: truonganhdung
 * Created Date: 10/22/2018
 * Modified Date: 10/23/2018
 * */

package guru99.II_VI_Read_Write;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Data_from_Excel_file {
    WebDriver driver;
    Read_Data_from_Excel_file objExcelFile;
    
    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		objExcelFile = new Read_Data_from_Excel_file();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/all-about-excel-in-selenium-poi-jxl.html
	@Test(enabled=true)
	public void TC_01() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\guru99\\III_X_Data_Driven\\excelExportAndFileIO";

	    objExcelFile.readExcel(filePath,"ExportExcel.xlsx","ExcelGuru99Demo");
	}
	
	public void readExcel(String filePath,String fileName,String sheetName) throws Exception{
	    File file = new File(filePath + "\\" + fileName);

	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook guru99Workbook = null;

	    String fileExtensionName = fileName.substring(fileName.indexOf("."));

	    if(fileExtensionName.equals(".xlsx")){
		    guru99Workbook = new XSSFWorkbook(inputStream);
		}
	    else if(fileExtensionName.equals(".xls")){
	        guru99Workbook = new HSSFWorkbook(inputStream);
	    }

	    Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

	    int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();

	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = guru99Sheet.getRow(i);

	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            System.out.print(row.getCell(j).getStringCellValue()+"|| ");
	        }
	        System.out.println();
	    }
	}
}
