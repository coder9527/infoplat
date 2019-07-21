package com.info.infoService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.baseService.dao.BaseDao;
import com.info.infoService.model.Info;
import com.info.infoService.service.InfoColumnsService;


@Service
public class InfoColumnsServiceImpl implements InfoColumnsService{
	
	@Autowired
	private BaseDao<Info> baseDao;

}
