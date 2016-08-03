package com.yonyou.mobilephone.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class APIApplication extends ResourceConfig{

	public APIApplication() {  
	     //服务类所在的包路径  
	     packages("com.yonyou.mobilephone.rest");  
	     //注册JSON转换器  
	     register(JacksonJsonProvider.class);  
	}  

}