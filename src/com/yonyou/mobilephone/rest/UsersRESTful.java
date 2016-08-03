package com.yonyou.mobilephone.rest;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
import net.sf.json.JsonConfig;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestBody;

import com.yonyou.idea.form.IdeaUpdateRecord;
import com.yonyou.integral.form.Integrallevel;
import com.yonyou.user.form.User;
import com.yonyou.util.HibernateSessionFactoryUtil;
import com.yonyou.util.JsonDateValueProcessor;

@Path("/mobilephone/users") 
@Consumes(MediaType.APPLICATION_JSON) 
public class UsersRESTful {


	@GET   
	@Path("/identifier")  
	public String check_login(@Context HttpServletRequest request) {  
		
		 
       
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		 Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		 Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			
			   //from后面是对象，不是表名
			   String hql="from User as user where user."+request.getParameter("loginType")+"=:identifier and password=:password";//使用命名参数，推荐使用，易读。
			   Query query=session.createQuery(hql);
			   query.setString("identifier", request.getParameter("identifier"));
			   query.setString("password", request.getParameter("password"));
			   
			   @SuppressWarnings("unchecked")
			   List<User> list=query.list();
			   
			  
			   if(list.size()==1){
				   
				   returnMap.put("message","success");
				   returnMap.put("code","1");
				   User user = list.get(0);
				   contentMap.put("userID",user.getUserId());
				   contentMap.put("userName", user.getUserName());
				   contentMap.put("country", user.getCountry());
				   contentMap.put("portraitUrl", user.getPortraitUrl());
				   
				   contentMap.put("reviewedFavorNumIdea", 0);
				   contentMap.put("reviewedFavorNumProject", 0);
				   contentMap.put("reviewedCommentNumIdea", 0);
				   contentMap.put("reviewedCommentNumProject", 0);
				   
				   contentMap.put("sentFavorNumIdea", 0);
				   contentMap.put("sentFavorNumProject", 0);
				   contentMap.put("sentCommentNumIdea", 0);
				   contentMap.put("sentCommentNumProject", 0);
				   
				   contentMap.put("myPublishIdeaNum", 0);
				   contentMap.put("myPublishProjectNum", 0);
				   
				   returnMap.put("content", contentMap);
			   }
			   else{
				   returnMap.put("message","fail");
				   returnMap.put("code","0");
				   returnMap.put("content", "");
			   }
			   
		}finally{
			
			session.getTransaction().commit();
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}  
	
	@GET   
	@Path("/{userID}/integral")  
	public String get_user_integral(@PathParam ("userID") int userID,@Context HttpServletRequest request) {  
		
		 
       
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		 Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		 Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
				User user = (User) session.get(User.class, userID);
		
				Criteria c=session.createCriteria(Integrallevel.class);
				c.add(Restrictions.gt("integralUp",user.getIntegral() ));
				c.add(Restrictions.and(Restrictions.lt("integralDown",user.getIntegral())));

				Integrallevel integrallevel =(Integrallevel) c.list().get(0);
				
				 float integralPercent= (float)user.getIntegral()/integrallevel.getIntegralUp();   
				 DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
				 String integralPercentStr = df.format(integralPercent);//返回的是String类型 
				
				c=session.createCriteria(User.class);
				c.add(Restrictions.eq("deleteFlag",0));
				c.addOrder(Order.desc("integral"));
				
				List<User> userList = c.list();
				
				Map<String, Object> rankingsMap01 = new LinkedHashMap<String, Object>();
				Map<String, Object> rankingsMap02 = new LinkedHashMap<String, Object>();
				Map<String, Object> rankingsMap03 = new LinkedHashMap<String, Object>();
				Map<String, Object> rankingsMapMy = new LinkedHashMap<String, Object>();
				List<Map<String, Object>> rankingsList = new ArrayList<Map<String,Object>>();
				
				
				rankingsMap01.put("userInfo", userList.get(0));
				rankingsMap01.put("ranking", 1);
				rankingsMap02.put("userInfo", userList.get(1));
				rankingsMap02.put("ranking", 2);
				rankingsMap03.put("userInfo", userList.get(2));
				rankingsMap03.put("ranking", 3);
				rankingsMapMy.put("myRrankings", userList.indexOf(user)+1);
				
				rankingsList.add(rankingsMap01);
				rankingsList.add(rankingsMap02);
				rankingsList.add(rankingsMap03);
				
				
				contentMap.put("integral",user.getIntegral());
				contentMap.put("integralLevel",integrallevel.getLevel());
				contentMap.put("integralPercent",integralPercentStr);
				contentMap.put("rankings", rankingsList);
				contentMap.put("myRranking",rankingsMapMy);
				
				returnMap.put("integralInfo", contentMap);
				returnMap.put("message","sucess");
				returnMap.put("code","1");

				
				
			   
		}finally{
			
			session.getTransaction().commit();
		}
		
		
		//JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
		
		JSONObject jsonObject = new JSONObject();
		  try {
		   JsonConfig jsonConfig = new JsonConfig(); 
		   jsonConfig.setExcludes(new String[]{"ideas"});
		   jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
		   jsonObject = JSONObject.fromObject(returnMap,jsonConfig);

		  } catch (Exception e) {
		  
		  }
		
	    return  jsonObject.toString();  
	}  
	
}
