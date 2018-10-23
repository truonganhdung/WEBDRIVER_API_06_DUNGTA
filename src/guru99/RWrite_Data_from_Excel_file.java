/* Author: truonganhdung
 * Created Date: 10/22/2018
 * Modified Date: 10/23/2018
 * */

package guru99;

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

public class RWrite_Data_from_Excel_file {
    WebDriver driver;
    RWrite_Data_from_Excel_file objExcelFile;
    
    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		//Create an object of ReadGuru99ExcelFile class
		objExcelFile = new RWrite_Data_from_Excel_file();
	}

	//( ._.')----------------------------------------------------
	//https://www.guru99.com/all-about-excel-in-selenium-poi-jxl.html
	@Test(enabled=true)
	public void TC_01() throws Exception{
		//Create an array with the data in the same order in which you expect to be filled in excel file
        String[] valueToWrite = {"Mr. E1","Noida"};

        //Write the file using file name, sheet name and the data to be filled
        objExcelFile.writeExcel(System.getProperty("user.dir")+"\\src\\excelExportAndFileIO","ExportExcel.xlsx","ExcelGuru99Demo",valueToWrite);
	}
	
	public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws Exception{
        //Create an object of File class to open xlsx file
        File file = new File(filePath + "//" + fileName);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook guru99Workbook = null;
        
        //Find the file extension by splitting  file name in substring and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if(fileExtensionName.contains(".xlsx")){
	        //If it is xlsx file then create object of XSSFWorkbook class
	        guru99Workbook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if(fileExtensionName.contains(".xls")){
            //If it is xls file then create object of XSSFWorkbook class
        	guru99Workbook = new HSSFWorkbook(inputStream);
        }
	    
	    //Read excel sheet by sheet name    
	    Sheet sheet = guru99Workbook.getSheet(sheetName);
	
	    //Get the current count of rows in excel file
	    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	    
	    //Get the first row from the sheet
	    Row row = sheet.getRow(0); //=> dòng này nè a, nó lấy row đầu tiên
	
	    //Create a new row and append it at last of sheet
	    Row newRow = sheet.createRow(rowCount+1);
	
	    //Create a loop over the cell of newly created Row
	    for (int j = 0; j < row.getLastCellNum(); j++) { // get column of this row, current has no data -> error
            Cell cell = newRow.createCell(j);
            if (j < dataToWrite.length) 
                cell.setCellValue(dataToWrite[j]);
            else
                break;
        }

	    //Close input stream
	    inputStream.close();
	
	    //Create an object of FileOutputStream class to create write data in excel file
	    FileOutputStream outputStream = new FileOutputStream(file);
	
	    //write data in the excel file
	    guru99Workbook.write(outputStream);
	
	    //close output stream
	    outputStream.close();
	}
}
