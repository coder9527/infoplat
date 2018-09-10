package com.info.userService.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.baseService.dao.BaseDao;
import com.info.userService.model.User;
import com.info.userService.service.UserService;

@Service
public class UserServiceImpl implements UserService{



	@Autowired
	private BaseDao<User> baseDao;

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return baseDao.selectByCriteria("users",baseDao.createCriteria(), User.class);
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saveUser(User user){
		UUID uuid=UUID.randomUUID();
		user.setId(uuid.toString().replace("-", ""));
		baseDao.save("users", "id", user);
	}

}
