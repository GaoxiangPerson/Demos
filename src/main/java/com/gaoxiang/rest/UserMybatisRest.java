package com.gaoxiang.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.gaoxiang.bean.User;
import com.gaoxiang.mybatis.service.UserService;

@Path("/user")
public class UserMybatisRest {

	@Autowired
	private UserService userService;
	
	@GET
	@Path("/all")
	public Response queryAllUser(){
		
		if(userService == null){
			return Response.status(200).entity("null").build();
		}
		List<User> resList =  userService.findAll();
		
		return Response.status(200).entity(resList.toString()).build();
	}
}
