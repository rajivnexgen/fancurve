package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fanselect.fancurve.BaseClass;
import pageObject.FanDetail;
import pageObject.Login;
import pageObject.Search;

public  class  TestCase1 extends BaseClass {
	Login lg ;
	@BeforeSuite
	public  void test1() throws InterruptedException {
		driverLoad();
		Login lg= new Login();
		
		lg.loginMain();
	}
	
	@Test(dataProvider="excelData",priority=2)
	public  void test2(String cfm, String st, String artNo) throws InterruptedException {
		FanDetail fd= new FanDetail();
		fd.search(cfm,st,artNo);
		
	}
	
	//@Test(dataProvider="excelData",priority=1)
	public  void test3(String cfm, String st, String artNo) throws InterruptedException {
		Search srch= new Search();
		srch.loginPage(cfm,st,artNo);
		
	}
	
	@DataProvider(name="excelData")
	public String[][] excel_data() throws IOException {
		File excelFile = new File("./resources/TEST1.xlsx");
		System.out.println(excelFile.exists());
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet1");
		int rowNum=sh.getPhysicalNumberOfRows();
		int colNum=sh.getRow(0).getLastCellNum();
		String[][] data= new String[rowNum-1][colNum];
		for(int i=0;i<rowNum-1;i++) {
			for(int j=0;j<colNum;j++) {
				DataFormatter df = new DataFormatter();
				data[i][j]=df.formatCellValue(sh.getRow(i+1).getCell(j));
			}
			
		}
		wb.close();
		fis.close();
		return data;
	}
	
	public void writeExcel(Double[][] data) throws IOException {
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
