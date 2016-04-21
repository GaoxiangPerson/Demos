package com.gaoxiang.service;

import org.springframework.stereotype.Service;

import com.gaoxiang.bean.User;

//@Service
public class UserServiceImpl implements UserService {
	
	
	public User queryByname(String name, String password) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName()+"queryByname");
		return null;
	}

	public boolean insert(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(User user, String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
