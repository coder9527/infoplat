package com.info.fileService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.fileService.model.FileCate;

import com.info.fileService.service.FileCateService;

@SpringBootApplication
/*@EnableEurekaClient*/
@CrossOrigin
@RestController
public class FileCateController {
	
	
	@Autowired
	private FileCateService fileCateService;
	
	@RequestMapping("/getFileCatesList")
	@ResponseBody
	public List<FileCate> getFileCatesList(){
		return fileCateService.getFileCates();
	}
	
	
	@RequestMapping("/getFileCatesListByParentId")
	@ResponseBody
	public List<FileCate> getFileCatesListByParentId(@RequestParam String parentId){
		return fileCateService.getFileCatesByParentId(parentId);
	}
	
	
	@RequestMapping("/getFileCatesListById")
	@ResponseBody
	public FileCate getFileCatesListById(@RequestParam String id){
		return fileCateService.getFileCate(id);
	}
	
	@RequestMapping("/getchildCatesList")
	@ResponseBody
	public List<FileCate> getchildCatesList(){
		return fileCateService.getchildCatesList();
	}
	
	
	@RequestMapping("/saveFileCate")
	public void saveFileInfo(@RequestBody FileCate fileCate){
		fileCateService.saveFileCate(fileCate);
	}
	
	@RequestMapping("/removFileCate")
	public void removeFileCate(@RequestParam String id){
		fileCateService.removeFileCate(id);
	}
	

}
