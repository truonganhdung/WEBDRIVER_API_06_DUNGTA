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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;

public class Write_Data_from_Excel_file {
    WebDriver driver;
    Write_Data_from_Excel_file objExcelFile;
    
    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		//Create an object of ReadGuru99ExcelFile class
		objExcelFile = new Write_Data_from_Excel_file();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/all-about-excel-in-selenium-poi-jxl.html
	@Test(enabled=true)
	public void TC_01() throws Exception{
        String[] valueToWrite = {"Mr. E1","Noida"};

        objExcelFile.writeExcel(System.getProperty("user.dir") + "\\src\\guru99\\III_X_Data_Driven\\excelExportAndFileIO","ExportExcel.xlsx","ExcelGuru99Demo",valueToWrite);
	}
	
	public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws Exception{
        File file = new File(filePath + "//" + fileName);

        FileInputStream inputStream = new FileInputStream(file);
        Workbook guru99Workbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if(fileExtensionName.contains(".xlsx")){
	        guru99Workbook = new XSSFWorkbook(inputStream);
        }
        else if(fileExtensionName.contains(".xls")){
        	guru99Workbook = new HSSFWorkbook(inputStream);
        }
  
	    Sheet sheet = guru99Workbook.getSheet(sheetName);

	    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	    
	    Row row = sheet.getRow(0);
	    
	    Row newRow = sheet.createRow(rowCount+1);

	    for (int j = 0; j < row.getLastCellNum(); j++) { // get column of this row, current has no data -> error
            Cell cell = newRow.createCell(j);
            if (j < dataToWrite.length) 
                cell.setCellValue(dataToWrite[j]);
            else
                break;
        }

	    inputStream.close();

	    FileOutputStream outputStream = new FileOutputStream(file);

	    guru99Workbook.write(outputStream);

	    outputStream.close();
	}
}
