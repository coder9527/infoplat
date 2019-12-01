package com.info.fileService.service;

import java.util.List;

import com.info.fileService.model.FileInfo;

public interface FileInfoService {


    /**
	 * 获取最新的信息 
	 */
	public List<FileInfo> getFileInfos();
	
	
	public List<FileInfo> getFileInfoScore();
	
	public List<FileInfo>getFileInfosCateNo(String CateNo);
	
	public List<FileInfo>getFileInfoRank(String type);
	
	
	public FileInfo getFileInfo(String id);
	
	public List<FileInfo> getFileInfoSearch(String searchTxt);
	
	
	public void saveFileInfo(FileInfo fileInfo);
	
	public void removeFile(String id);
	
	public List<FileInfo> getFileInfoNews(String type);

}
