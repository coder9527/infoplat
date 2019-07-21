package com.info.fileService.service;

import java.util.List;

import com.info.fileService.model.FileInfo;

public interface FileInfoService {


    /**
	 * 获取最新的信息 
	 */
	public List<FileInfo> getFileInfos();
	
	
	public FileInfo getFileInfo(String id);

}
