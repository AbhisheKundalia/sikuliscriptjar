package sikulia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	 public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File src = new File("C:\\Users\\Abhishek Jain\\Desktop\\yii\\Sikuli\\data\\test.xlsx");
		ReadExcel rExcel = new ReadExcel();
		int rowNum = rExcel.getRowCount(src);
		for(int i = 0; i < rowNum; i++)
		{
			String trackinId = rExcel.readExcel(src, i, 0);
			String requestType = rExcel.readExcel(src, i, 1);
			System.out.println("Data from excel at "+i+",0 :"+trackinId);
			System.out.println("Data from excel at "+i+",1 :"+requestType);
			if(trackinId.equalsIgnoreCase("username6"))
			{
				rExcel.writeExcel(src, i, 2, "Pass");
			}
			else
				rExcel.writeExcel(src, i, 2, trackinId);
		}
		
		
	}

	String readExcel(File src, int row, int column) throws Exception
	{
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		String data = sheet1.getRow(row).getCell(column).getStringCellValue();  
		wb.close();
		return data;
	}
	
	public int getRowCount(File src) throws Exception
	{
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		int rowNum = sheet1.getLastRowNum();
		wb.close();
		return rowNum + 1;
	}
	
	public void writeExcel(File src, int row, int column, String str) throws Exception
	{
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		sheet1.getRow(row).createCell(column).setCellValue(str);
		FileOutputStream fos = new FileOutputStream(src);
		wb.write(fos);
		wb.close();
	}
}
