package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
   Workbook wb;
   //Constructor for Reading the path of the Excel File
   public ExcelFileUtil(String ExcelPath) throws Throwable {
	   FileInputStream fi = new FileInputStream(ExcelPath);
	   wb = WorkbookFactory.create(fi);
   }
   //Method for Counting Number of Rows in Sheet
   public int rowCount(String sheetName) {
	   return wb.getSheet(sheetName).getLastRowNum();
   }
   //Method for Reading Cell Data
   public String getCellData(String sheetName,int row,int cell) {
	   String data = "";
	   if(wb.getSheet(sheetName).getRow(row).getCell(cell).getCellType()==CellType.NUMERIC) {
		   int celldata = (int) wb.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
		   data = String.valueOf(celldata);
	   }
	   else {
		   data = wb.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
	   }
	return data;
   }
   //Method for Writing Data into Results Column
   public void setCellData(String sheetName,int row,int cell,String status,String writeExcel) throws Throwable {
	   
	   //Write Data into above cell
	   wb.getSheet(sheetName).getRow(row).createCell(cell).setCellValue(status);
	   if(status.equalsIgnoreCase("Pass")) {
		   CellStyle style = wb.createCellStyle();
		   Font font = wb.createFont();
		   font.setColor(IndexedColors.GREEN.getIndex());
		   font.setBold(true);
		   style.setFont(font);
		   wb.getSheet(sheetName).getRow(row).getCell(cell).setCellStyle(style);
	   }
	   else if(status.equalsIgnoreCase("Fail")){
		   CellStyle style = wb.createCellStyle();
		   Font font = wb.createFont();
		   font.setColor(IndexedColors.RED.getIndex());
		   font.setBold(true);
		   style.setFont(font);
		   wb.getSheet(sheetName).getRow(row).getCell(cell).setCellStyle(style);
	   }
	   else if(status.equalsIgnoreCase("Blocked")) {
		   CellStyle style = wb.createCellStyle();
		   Font font = wb.createFont();
		   font.setColor(IndexedColors.BLUE.getIndex());
		   font.setBold(true);
		   style.setFont(font);
		   wb.getSheet(sheetName).getRow(row).getCell(cell).setCellStyle(style);
	   }
	   FileOutputStream fo = new FileOutputStream(writeExcel);
	   wb.write(fo);
   }
   public static void main(String[] args) throws Throwable {
	ExcelFileUtil xl = new ExcelFileUtil("D:/ExcelMethods.xlsx");
	//Count Number of Rows in Emp Sheet
	int rc = xl.rowCount("Emp");
	System.out.println("Number of Rows i"
			+ "n a Sheet are::"+rc);
	for(int i=1;i<=rc;i++) {
		String fname = xl.getCellData("Emp", i, 0);
		String mname = xl.getCellData("Emp", i, 1);
		String lname = xl.getCellData("Emp", i, 2);
		String eid = xl.getCellData("Emp", i, 3);
		System.out.println(fname+"    "+mname+"    "+lname+"    "+eid);
		xl.setCellData("Emp", i, 4, "PASS", "D:/Results.xlsx");
		//xl.setCellData("Emp", i, 4, "FAIL", "D:/Results.xlsx");
		//xl.setCellData("Emp", i, 4, "BLOCKED", "D:/Results.xlsx");
	}
}
}
