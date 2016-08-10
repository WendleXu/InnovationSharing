package com.yonyou.util;

import org.hibernate.SessionFactory;   
import org.hibernate.cfg.Configuration;  
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
  
public class HibernateSessionFactoryUtil {  
    private static final SessionFactory sessionFactory;  
    static {  
        try {  
        	//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        	ApplicationContext wac = new ClassPathXmlApplicationContext("applicationContext.xml");
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