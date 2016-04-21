package com.gaoxiang.mybatis.dao;

import java.util.List;

import com.gaoxiang.bean.User;

/**
 * 
 * @author gaoxiang
 * 
 *
 */
public interface UserMapper {

	public List<User> findAll();
	
	/*public User queryById(int id);
	
	public User queryByName(String name);
	
	public User queryByUser(String name,String password);
	
	public void saveUser(User user);
	
	public void deleteUser(int id);
	
	public void updateUser(User user);*/
}
