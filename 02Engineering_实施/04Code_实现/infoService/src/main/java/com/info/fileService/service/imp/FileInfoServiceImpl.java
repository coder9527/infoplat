package com.info.fileService.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.baseService.dao.BaseDao;
import com.info.fileService.model.FileInfo;
import com.info.fileService.service.FileInfoService;
import com.info.infoService.model.Info;



@Service
public class FileInfoServiceImpl implements FileInfoService{
	
	@Autowired
	private BaseDao<FileInfo> baseDao;

	@Override
	public List<FileInfo> getFileInfos() {
		// TODO Auto-generated method stub
		return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria(), FileInfo.class);
		
	}

	@Override
	public FileInfo getFileInfo(String id) {
		// TODO Auto-generated method stub
		return baseDao.selectById("fileInfos", "id", id, FileInfo.class);
	}
	
	
	

}
