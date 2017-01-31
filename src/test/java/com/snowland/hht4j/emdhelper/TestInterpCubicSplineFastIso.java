package com.snowland.hht4j.emdhelper;

import com.snowland.hht4j.EMDHelper;
import com.snowland.hht4j.dataimport.DataImport;
import com.snowland.hht4j.dataimport.DataImportResult;
import com.snowland.hht4j.result.GetIMFResult;

public class TestInterpCubicSplineFastIso {
	public static void main(String[] args) {
		String filename = "src/test/resources/importdata.xls";
		try {
			DataImportResult dataImportResult = DataImport.load(filename);
			
			double []x = dataImportResult.getX();
			double []y = dataImportResult.getY();
			EMDHelper emdHelper = new EMDHelper(x, y);
			GetIMFResult getIMFResult = emdHelper.getIMF();
			
			double [] imf = getIMFResult.getImf();
			for(double each : imf){
				System.out.println(each);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
