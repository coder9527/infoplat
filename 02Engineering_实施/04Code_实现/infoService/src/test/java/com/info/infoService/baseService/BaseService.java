package com.info.infoService.baseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.info.baseService.util.ExcelUtil;

public class BaseService {
	
	@Test
	public void testExcelImport() throws IOException{
		
		File file=new File("C:\\Users\\lijin\\Desktop\\数据.xls");
		FileInputStream in = new FileInputStream(file); 
		Workbook workbook=ExcelUtil.getWorkbok(in,file);
		Sheet sheet = workbook.getSheetAt(0); 
		
		for (Row row : sheet) {
            for (int i = 0; i < 4; i++) {
            	Cell cell = row.getCell(i);
            	Object obj = ExcelUtil.getValue(cell);
            	System.out.println(obj.toString());
            }
		}
		
	}

}
