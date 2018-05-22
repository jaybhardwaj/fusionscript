package Framework.TestingFramework.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelRead 
{
	public String[][] readExcel(String filePath,String fileName,String sheetName) throws IOException
    {
		String[][] arrayExcelData = null;

    File file =    new File(filePath+"//"+fileName);
    System.out.println(file);

    FileInputStream inputStream = new FileInputStream(file);
    Workbook FusionWorkbook = null;

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    if(fileExtensionName.equals(".xlsx"))
     {
       FusionWorkbook = new XSSFWorkbook(inputStream);
     }
     else if(fileExtensionName.equals(".xls"))
     {
    	FusionWorkbook = new HSSFWorkbook(inputStream);
     }

    //Read sheet inside the workbook by its name
    Sheet FusionSheet = FusionWorkbook.getSheet(sheetName);

    //Find number of rows in excel file
    int rowCount = FusionSheet.getLastRowNum()+1;
   // System.out.println("rowcount=="+rowCount);
   	int columnlength = FusionSheet.getRow(0).getLastCellNum();
   	//System.out.println("ColumnCount=="+columnlength);
    arrayExcelData = new String[rowCount][columnlength];

    //Create a loop over all the rows of excel file to read it
    for (int i = 0; i < rowCount; i++) 
      {
    	
        Row row = FusionSheet.getRow(i);
        for (int j = 0; j < row.getLastCellNum(); j++) 
        {
        	Cell cell = row.getCell(j);
        	CellType type = cell.getCellTypeEnum();
            if (type == CellType.STRING) 
            {
               /* System.out.println("[" + cell.getRowIndex() + ", "
                        + cell.getColumnIndex() + "] = STRING; Value = "
                        + cell.getRichStringCellValue().toString());*/
                arrayExcelData[i][j]=cell.getRichStringCellValue().toString();
            }
            else if (type == CellType.NUMERIC) 
            {
               /* System.out.println("[" + cell.getRowIndex() + ", "
                        + cell.getColumnIndex() + "] = NUMERIC; Value = "
                        + cell.getNumericCellValue());*/
                arrayExcelData[i][j]=Integer.toString((int) cell.getNumericCellValue());
            }
            else if (type == CellType.BOOLEAN) 
            {
                /*System.out.println("[" + cell.getRowIndex() + ", "
                        + cell.getColumnIndex() + "] = BOOLEAN; Value = "
                        + cell.getBooleanCellValue());*/
                arrayExcelData[i][j]=String.valueOf(cell.getBooleanCellValue());
            } 
            else if (type == CellType.BLANK) 
            {
                /*System.out.println("[" + cell.getRowIndex() + ", "
                        + cell.getColumnIndex() + "] = BLANK CELL");*/
                arrayExcelData[i][j]=null;
            }
        	
        }
         System.out.println();
    }
    
	return arrayExcelData;

    

    }

}
