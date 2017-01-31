package com.snowland.hht4j.dataimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataImport {
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static DataImportResult load(File filename) throws Exception{
		
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			return load(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 
	 * @param fileInputStream 
	 * @return
	 * @throws Exception
	 */
	public static DataImportResult load(InputStream fileInputStream) throws Exception{
	
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheet(0);
			int rows = sheet.getRows();
			double []x = new double[rows - 1];
			double []y = new double[rows - 1];
			NumberCell xNumberCell = null, yNumberCell = null;
			Cell xCell, yCell;
			
			for(int i = 0; i < rows - 1; ++i){
//				System.out.println(i);
				xCell = sheet.getCell(0, i+1);
				yCell = sheet.getCell(1, i+1);
//				System.out.println(xCell.getContents());
//				System.out.println(yCell.getContents());
				if(xCell.getType() == CellType.NUMBER){
					xNumberCell = (NumberCell)xCell;
					yNumberCell = (NumberCell)yCell;
					x[i] = xNumberCell.getValue();
					y[i] = yNumberCell.getValue();
				} else {
					System.out.println("´íÎóÊý¾Ý");
					x[i] = Double.NaN;
					y[i] = Double.NaN;
				}
				
			}
			DataImportResult result = new DataImportResult();
			result.setX(x);
			result.setY(y);
			
			return result;
		} catch (BiffException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 */
	public static DataImportResult load(String filename) throws Exception{
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			
			return load(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}




}
