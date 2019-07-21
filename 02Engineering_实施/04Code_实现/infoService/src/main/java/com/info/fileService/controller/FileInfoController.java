package com.info.fileService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.fileService.model.FileInfo;
import com.info.fileService.service.FileInfoService;
import com.info.infoService.model.Info;


@SpringBootApplication
/*@EnableEurekaClient*/
@CrossOrigin
@RestController
public class FileInfoController {

	@Autowired
	private FileInfoService fileInfoService;
	
	
	
	@RequestMapping("/getFileInfosList")
	@ResponseBody
	public List<FileInfo> getFileInfosList(){
		return fileInfoService.getFileInfos();
	}
	
	
	@RequestMapping("/getFileInfo")
	@ResponseBody
	public FileInfo getFileInfo(@RequestParam String id){
		
		return fileInfoService.getFileInfo(id);
	}
	
	
}