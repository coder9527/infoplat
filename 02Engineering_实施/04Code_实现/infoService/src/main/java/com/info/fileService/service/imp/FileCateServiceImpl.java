package com.info.fileService.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.baseService.dao.BaseDao;
import com.info.fileService.model.FileCate;
import com.info.fileService.service.FileCateService;



@Service
public class FileCateServiceImpl implements FileCateService{
	
	@Autowired
	private BaseDao<FileCate> baseDao;

	@Override
	public List<FileCate> getFileCates() {
		// TODO Auto-generated method stub
		return baseDao.selectByCriteria("filecates",baseDao.createCriteria(), FileCate.class);
		
	}

	@Override
	public FileCate getFileCate(String id) {
		// TODO Auto-generated method stub
		return baseDao.selectById("filecates", "id", id, FileCate.class);
	}

	@Override
	public List<FileCate> getFileCatesByParentId(String parentId) {
		// TODO Auto-generated method stub
		return baseDao.selectByCriteria("filecates",baseDao.createCriteria().eq("parentId", parentId), FileCate.class);
	
	}
	
	
	
	
	

}
