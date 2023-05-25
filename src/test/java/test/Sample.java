package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Sample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int[][] data= {{2000,30},{4000,40},{1000,60}};
		System.out.println(data.length);
		
		
			XSSFWorkbook wb= new XSSFWorkbook();
			XSSFSheet sh = wb.createSheet("Sheet1");
			
			System.out.println(data[0][0]);
			System.out.println(data[0][1]);
			for(int i=0;i<data.length;i++) {
				Row row=sh.createRow(i);
			for(int j=0;j<data[0].length;j++) {
			
			row.createCell(j).setCellValue(data[i][j]);
			
			}
			}
			File f= new File(".//resources//result.xlsx");
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			fos.close();
			wb.close();
			
		
	}

}
