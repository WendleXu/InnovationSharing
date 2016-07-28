package com.yonyou.util;

import org.hibernate.SessionFactory;   
import org.hibernate.cfg.Configuration;  
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
  
public class HibernateSessionFactoryUtil {  
    private static final SessionFactory sessionFactory;  
    static {  
        try {  
        	WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            sessionFactory = (SessionFactory)wac.getBean("sessionFactory");  
        } catch (Throwable ex) {  
              
            throw new ExceptionInInitializerError(ex);  
        }  
    }  
  
    private HibernateSessionFactoryUtil() {  
          
    }  
  
    public static SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
}  