package com.info.infoService.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.baseService.dao.BaseDao;
import com.info.infoService.model.Info;
import com.info.infoService.service.InfoService;
import com.info.repertory.model.Repertory;


@Service
public class InfoServiceImpl implements InfoService{
	@Autowired
	private BaseDao<Info> baseDao;

	@Override
	public List<Info> getInfosList() {
		// TODO Auto-generated method stub
		return baseDao.selectByCriteria("infos",baseDao.createCriteria(), Info.class);
	}

	@Override
	public void saveInfo(Info info) {
		// TODO Auto-generated method stub
		UUID uuid=UUID.randomUUID();
		info.setId(uuid.toString().replace("-", ""));
		baseDao.save("infos", "id", info);
		
	}

	@Override
	public List<Info> getInfosByColumn(String columnId) {
		// TODO Auto-generated method stub
		return baseDao.selectByCriteria("infos", baseDao.createCriteria().eq("column_id", columnId), Info.class);
	}

	@Override
	public List<Info> getCurrentInfos() {
		// TODO Auto-generated method stub
		return null;
	}

}
