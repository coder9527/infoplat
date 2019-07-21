package com.info.fileService.service;

import java.util.List;

import com.info.fileService.model.FileCate;



public interface FileCateService {

	
	public List<FileCate> getFileCates();
	
	
	public List<FileCate> getFileCatesByParentId(String parentId);
	
	
	public FileCate getFileCate(String id);
}
