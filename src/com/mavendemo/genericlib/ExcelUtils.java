package com.mavendemo.genericlib;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	/*
	 * 	@description readData() will read fromtestData.xslx
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return String
	 * 
	 * 
	 */

	//public static final String filePath=".\\testData\\testData.xlsx";
	
/*	public static String readData(String sheetName , int rowNum, int cellNum)
	{		String value=""; // we can also write this >>  String value=null;
		try{
		Workbook wb=WorkbookFactory.create(new FileInputStream(new File(filePath)));
		value=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		}
		catch(EncryptedDocumentException e){
			e.printStackTrace();
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return value;
	}
	
	public static void setExcelData(int rowNum, int cellNum, String data){
		try{
			Workbook wb=WorkbookFactory.create(new FileInputStream(new File(filePath)));
			Cell c=wb.getSheet("Sheet1").getRow(rowNum).createCell(cellNum);
			c.setCellType(CellType.STRING);
			FileOutputStream fos=new FileOutputStream(new File(filePath));
			c.setCellValue(data);
			wb.write(fos);
		}
		catch(EncryptedDocumentException e){
		}
		catch (InvalidFormatException e) {
		}
		catch(FileNotFoundException e){
		}
		catch(IOException e){
		}
		
	}*/
	
	public static String[] toReadExcelData(String sTestCaseID)
	{
		String sData[]=null;
		try{
			FileInputStream fis=new FileInputStream(new File(".\\testData\\testData.xlsx"));
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sht=	wb.getSheet("Sheet1");
			int iRowNum=sht.getLastRowNum();

			for(int i=1;i<=iRowNum;i++)
			{
				if(sht.getRow(i).getCell(0).toString().equals(sTestCaseID))	
				{
					int iCellNum=sht.getRow(i).getLastCellNum();
					sData=new String[iCellNum];

					for(int j=0;j<iCellNum;j++)
					{
						sData[j]= sht.getRow(i).getCell(j).getStringCellValue(); //it is work like this  s[1]= "Test";
					}
					break;
				}
				
			}
		}
		// end of try
		catch(EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return sData;
	}

	public static void writeData(Sheet s, int rowNum,String data ,int Value)
	{
	Row r=s.createRow(rowNum);
	r.createCell(0).setCellValue(data);
	r.createCell(1).setCellValue(Value);
	}
}
	
	
	
	

