package com.yonyou.mobilephone.rest;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;  
import javax.ws.rs.Produces;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;  

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestBody;

import com.yonyou.user.form.User;
import com.yonyou.util.HibernateSessionFactoryUtil;

@Path("/mobilephone/users") 
@Consumes(MediaType.APPLICATION_JSON) 
public class UsersRESTful {


	@GET   
	@Path("/identifier")  
	public String check_login(@Context HttpServletRequest request) {  
		
		 
       
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		 Map<String, Object> return_map = new LinkedHashMap<String, Object>();
		 Map<String, Object> content_map = new LinkedHashMap<String, Object>();
		try{
			
			
			   //from后面是对象，不是表名
			   String hql="from User as user where user."+request.getParameter("loginType")+"=:identifier and password=:password";//使用命名参数，推荐使用，易读。
			   Query query=session.createQuery(hql);
			   query.setString("identifier", request.getParameter("identifier"));
			   query.setString("password", request.getParameter("password"));
			   
			   @SuppressWarnings("unchecked")
			   List<User> list=query.list();
			   
			  
			   if(list.size()==1){
				   
				   return_map.put("message","success");
				   return_map.put("code","1");
				   User user = list.get(0);
				   content_map.put("userID",user.getUserId());
				   content_map.put("userName", user.getUserName());
				   content_map.put("country", user.getCountry());
				   content_map.put("portraitUrl", user.getPortraitUrl());
				   
				   content_map.put("reviewedFavorNumIdea", 0);
				   content_map.put("reviewedFavorNumProject", 0);
				   content_map.put("reviewedCommentNumIdea", 0);
				   content_map.put("reviewedCommentNumProject", 0);
				   
				   content_map.put("sentFavorNumIdea", 0);
				   content_map.put("sentFavorNumProject", 0);
				   content_map.put("sentCommentNumIdea", 0);
				   content_map.put("sentCommentNumProject", 0);
				   
				   content_map.put("myPublishIdeaNum", 0);
				   content_map.put("myPublishProjectNum", 0);
				   
				   return_map.put("content", content_map);
			   }
			   else{
				   return_map.put("message","fail");
				   return_map.put("code","0");
				   return_map.put("content", "");
			   }
			   
		}finally{
			
			session.getTransaction().commit();
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(return_map);
		
	    return  jsonObject.toString();  
	}  
	
	
}
