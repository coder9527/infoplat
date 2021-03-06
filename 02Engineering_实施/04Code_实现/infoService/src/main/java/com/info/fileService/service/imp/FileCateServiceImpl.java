package com.info.fileService.service.imp;

import java.util.List;
import java.util.UUID;

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
		String [] sortColum=new String[1];
		sortColum[0]="cateNo";
		return baseDao.selectByCriteria("filecates",baseDao.createCriteria().order(sortColum,"asc"), FileCate.class);
		
	}

	@Override
	public FileCate getFileCate(String id) {
		// TODO Auto-generated method stub
		return baseDao.selectById("filecates", "id", id, FileCate.class);
	}

	@Override
	public List<FileCate> getFileCatesByParentId(String parentId) {
		// TODO Auto-generated method stub
		String [] sortColum=new String[1];
		sortColum[0]="cateNo";
		return baseDao.selectByCriteria("filecates",baseDao.createCriteria().eq("parentId", parentId).order(sortColum,"asc"), FileCate.class);
	
	}
	
	@Override
	public List<FileCate>  getchildCatesList(){
		String [] sortColum=new String[1];
		sortColum[0]="cateNo";
		return baseDao.selectByCriteria("filecates",baseDao.createCriteria().not().eq("parentId", "0").order(sortColum,"asc"), FileCate.class);
	}

	@Override
	public void saveFileCate(FileCate fileCate) {
		// TODO Auto-generated method stub
		UUID uuid=UUID.randomUUID();
		fileCate.setId(uuid.toString().replace("-", ""));
		baseDao.save("filecates", "id", fileCate);
	}
	
	
	@Override
	public void removeFileCate(String id){
		baseDao.removeById("filecates", "id", id);
	}
	

}
