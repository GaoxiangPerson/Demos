package com.gaoxiang.service;

import com.gaoxiang.bean.User;

public interface UserService {

		public User queryByname(String name,String password);
		
		public boolean insert(User user);
		
		public boolean update(User user,String name);
		
		
}
