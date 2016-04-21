package com.gaoxiang.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.gaoxiang.service.UserService;

@Path("/hello")
public class UserRestController {

//	  @Autowired
	  private UserService userService;
	 
	  
	  
	  @GET
	  @Path("/queryUser")
	 // @Produces(MediaType.TEXT_PLAIN)
	  public String getUser() {
		  
		 if(userService == null){
			   return "userService is null";
		 }else{
			 userService.queryByname(null, null); 
		 }
		 
	    return "Hello Jersey";
	  }
}
