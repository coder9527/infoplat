package com.info.fileService.service;

import java.util.List;

import com.info.fileService.model.FileCate;



public interface FileCateService {

	
	public List<FileCate> getFileCates();
	
	
	public List<FileCate> getFileCatesByParentId(String parentId);
	
	
	
	public List<FileCate>  getchildCatesList();
	
	
	public FileCate getFileCate(String id);
	
	
	public void saveFileCate(FileCate fileCate);
	
	public void removeFileCate(String id);
}
