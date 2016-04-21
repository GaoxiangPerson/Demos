package com.gaoxiang.mybatis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoxiang.bean.User;
import com.gaoxiang.mybatis.dao.UserMapper;
import com.gaoxiang.mybatis.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;
	
	public List<User> findAll() {
		return userDao.findAll();
	}


}
