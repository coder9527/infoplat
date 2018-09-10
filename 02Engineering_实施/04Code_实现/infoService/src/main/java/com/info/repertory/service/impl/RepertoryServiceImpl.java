package com.info.repertory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.baseService.dao.BaseDao;
import com.info.repertory.model.Repertory;
import com.info.repertory.service.RepertoryService;



@Service
public class RepertoryServiceImpl implements RepertoryService{
	
	@Autowired
	private BaseDao<Repertory> baseDao;

	@Override
	public List<Repertory> getRepertory() {
		// TODO Auto-generated method stub
		return baseDao.selectByCriteria("repertory",baseDao.createCriteria(), Repertory.class);
	}

	@Override
	public void updateRepertory(Repertory repertory) {
		// TODO Auto-generated method stub
		 baseDao.save("repertory", "id", repertory);
		
	}

	@Override
	public Repertory getRepertoryById(String id) {
		// TODO Auto-generated method stub
		return baseDao.selectById("repertory", "id", id, Repertory.class);
	}
	
	

}
