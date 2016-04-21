package com.gaoxiang;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.gaoxiang.rest.UserMybatisRest;
import com.gaoxiang.rest.UserRestController;

public class APIApplication extends ResourceConfig {
	public APIApplication() {
		  //加载Resource
		  register(UserRestController.class);
		  register(UserMybatisRest.class);
		  //注册数据转换器
		  register(JacksonJsonProvider.class);

		  // Logging.
		/*  register(LoggingFilter.class);*/
		  
		  /**
		   * 
		   */
		  
		  /**
		   * 
		   */
		  
		  
		  
		  ///////
		    }
		
}
