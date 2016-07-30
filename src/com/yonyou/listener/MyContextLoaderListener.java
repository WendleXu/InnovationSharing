package com.yonyou.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class MyContextLoaderListener extends ContextLoaderListener {  
  
    public void contextDestroyed(ServletContextEvent sce) {  
        // TODO Auto-generated method stub  
  
    }  
  
    public void contextInitialized(ServletContextEvent sce) {  
        // TODO Auto-generated method stub  
        String webAppRootKey = sce.getServletContext().getRealPath("/");  
        System.setProperty("csc2.root" , webAppRootKey);  
        String path =System.getProperty("csc2.root");  
        System.out.println("sssss:::"+path);  
    }  
  
}  