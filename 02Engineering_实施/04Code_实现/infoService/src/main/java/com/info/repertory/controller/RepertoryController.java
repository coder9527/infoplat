package com.info.repertory.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.info.baseService.util.ExcelUtil;
import com.info.baseService.util.UUIDGenerator;
import com.info.repertory.model.Repertory;
import com.info.repertory.service.RepertoryService;

@SpringBootApplication
/*@EnableEurekaClient*/
@RestController
@CrossOrigin
public class RepertoryController {
	private static Logger logger = Logger.getLogger(RepertoryController.class);
	
	@Autowired
	private RepertoryService repertoryService;
	
	
	@RequestMapping("/getRepertoryList")
	@ResponseBody
	public List<Repertory> getRepertoryList(){
		return repertoryService.getRepertory();
	}
	
	
	@RequestMapping("/updateRepertory")
	public void updateRepertory(String repertoryId,String number){
		//
		System.out.println("-----"+JSON.toJSONString(repertoryId)+"-"+number);
		//
		Repertory tempRepertory=repertoryService.getRepertoryById(repertoryId);
		tempRepertory.setCountNum(number);
		repertoryService.updateRepertory(tempRepertory);
	}
	
	@RequestMapping("/excelImport")
	public void excelImport() throws IOException{
		File file=new File("C:\\Users\\lijin\\Desktop\\数据.xls");
		FileInputStream in = new FileInputStream(file); 
		Workbook workbook=ExcelUtil.getWorkbok(in,file);
		Sheet sheet = workbook.getSheetAt(0); 
		
		for (int j=1;j<=sheet.getLastRowNum();j++) {
			Row row=sheet.getRow(j);
			Repertory repertory=new Repertory();
			
			if(null!=row.getCell(0)){
				row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
			}
			repertory.setProductName(row.getCell(0)==null?"":row.getCell(0).getStringCellValue().toString());
			
			if(null!=row.getCell(1)){
				row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
			}
			repertory.setProductNo(row.getCell(1)==null?"":row.getCell(1).getStringCellValue().toString());
			if(null!=row.getCell(2)){
				row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
			}
			repertory.setCountNum(row.getCell(2)==null?"":row.getCell(2).getStringCellValue().toString());
			
			if(null!=row.getCell(3)){
				row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
			}
			repertory.setRepertoryNum(row.getCell(3)==null?"":row.getCell(3).getStringCellValue().toString());
			logger.info("----->>>"+JSON.toJSONString(repertory));
			repertory.setId(UUIDGenerator.generate());
			repertoryService.updateRepertory(repertory);
		}
	}

}
