package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Parameterization 
{

	public static String getStringdata(String sheetname, int row, int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream file = new FileInputStream("C:\\Users\\HP\\Desktop\\QA Notes\\Automation\\TestNaaptol.xlsx");
	    String value =WorkbookFactory.create(file).getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
	    		
		return value;		
	}
}