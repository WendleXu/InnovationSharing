package com.yonyou.mobilephone.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class APIApplication extends ResourceConfig{

	public APIApplication() {  
	     //���������ڵİ�·��  
	     packages("com.yonyou.mobilephone.rest");  
	     //ע��JSONת����  
	     register(JacksonJsonProvider.class);  
	}  

}