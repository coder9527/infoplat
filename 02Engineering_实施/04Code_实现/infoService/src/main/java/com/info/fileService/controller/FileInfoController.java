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

import com.info.fileService.model.FileInfo;
import com.info.fileService.service.FileInfoService;


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
	
	@RequestMapping("/getFileInfosCateNo")
	@ResponseBody
	public List<FileInfo> getFileInfosList(@RequestParam String cateNo){
		return fileInfoService.getFileInfosCateNo(cateNo);
	}
	
	
	
	@RequestMapping("/getFileInfo")
	@ResponseBody
	public FileInfo getFileInfo(@RequestParam String id){
		return fileInfoService.getFileInfo(id);
	}
	
	
	@RequestMapping("/getFileInfoRank")
	@ResponseBody
	public List<FileInfo> getFileInfoRank(@RequestParam String type){
		return fileInfoService.getFileInfoRank(type);
	}
	
	@RequestMapping("/getFileInfoScore")
	@ResponseBody
	public List<FileInfo> getFileInfoScore(){
		return fileInfoService.getFileInfoScore();
	}
	
	
	
	@RequestMapping("/getFileInfoSearch")
	@ResponseBody
	public List<FileInfo> getFileInfoSearch(@RequestParam String searchText){
		return fileInfoService.getFileInfoSearch(searchText);
	}
	
	

	@RequestMapping("/saveFileInfo")
	public void saveFileInfo(@RequestBody FileInfo fileInfo){
		fileInfoService.saveFileInfo(fileInfo);
	}
	
	@RequestMapping("/removeFile")
	public void removeFile(@RequestParam String id){
		fileInfoService.removeFile(id);
	}
	
	//最新更新
	//全部 安卓
	@RequestMapping("/getFileInfoNewsList")
	@ResponseBody
	public List<FileInfo> getFileInfoNews(@RequestParam String type){
		
		//return fileInfoService.getFileInfoSearch(searchText);
		return fileInfoService.getFileInfoNews(type);
	}
	
	
}