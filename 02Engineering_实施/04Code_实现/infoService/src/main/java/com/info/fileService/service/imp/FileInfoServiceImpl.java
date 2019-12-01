package com.info.fileService.service.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.baseService.dao.BaseDao;
import com.info.fileService.model.FileInfo;
import com.info.fileService.service.FileInfoService;



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

	@Override
	public void saveFileInfo(FileInfo fileInfo) {
		// TODO Auto-generated method stub
		UUID uuid=UUID.randomUUID();
		fileInfo.setId(uuid.toString().replace("-", ""));
		baseDao.save("fileInfos", "id", fileInfo);
	}
	
	
	@Override
	public List<FileInfo>getFileInfosCateNo(String CateNo){
		return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().like("cateNo", CateNo), FileInfo.class);
	}
	
	@Override
	public List<FileInfo> getFileInfoRank(String type){
		if("week".equals(type)){
			return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().order(new String[]{"weekDownCount"}, "desc"), FileInfo.class);
		
		}else if("month".equals(type)){
			return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().order(new String[]{"monthDownCount"}, "desc"), FileInfo.class);
			
		}else{
			return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().order(new String[]{"totalDownCount"}, "desc"), FileInfo.class);
			
		}
			
	}
	
	@Override
	public List<FileInfo> getFileInfoSearch(String searchTxt){
		return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().like("softDesc", searchTxt).or().like("title", searchTxt), FileInfo.class);
		
	}
	
	
	@Override
	public List<FileInfo> getFileInfoScore(){
		return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().order(new String[]{"score"}, "desc"), FileInfo.class);
		
	}
	
	
	@Override
	public void removeFile(String id){
		baseDao.removeById("fileInfos", "id", id);
	}
	
	
	@Override
	public List<FileInfo> getFileInfoNews(String type){
		if("all".equals(type)){
			return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().order(new String[]{"updateTime"}, "desc"), FileInfo.class);
		}else if("android".equals(type)){
			return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().eq("applyPlat", "android").order(new String[]{"updateTime"}, "desc"), FileInfo.class);
		}else if("ios".equals(type)){
			return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().eq("applyPlat", "ios").order(new String[]{"updateTime"}, "desc"), FileInfo.class);
		}else{
			return baseDao.selectByCriteria("fileInfos",baseDao.createCriteria().eq("applyPlat", "windows").order(new String[]{"updateTime"}, "desc"), FileInfo.class);
		}
		
		
	}
	
	
	

}
