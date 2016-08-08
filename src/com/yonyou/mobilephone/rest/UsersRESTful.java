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
import com.yonyou.softversion.SoftVersion;
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
			   String hql="from User as user where user."+request.getParameter("loginType")+"=:identifier and password=:password and deleteFlag=0";//使用命名参数，推荐使用，易读。
			   Query query=session.createQuery(hql);
			   query.setString("identifier", request.getParameter("identifier"));
			   query.setString("password", request.getParameter("password"));
			   
			   @SuppressWarnings("unchecked")
			   List<User> list=query.list();
			   
			  
			   if(list.size()==1){
				   
				   returnMap.put("message","success");
				   returnMap.put("code","1");
				   User user = list.get(0);
				   contentMap.put("userId",user.getUserId());
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
	@Path("/{userId}/integral")  
	public String get_users_integral(@PathParam ("userId") int userId,@Context HttpServletRequest request) {  
		
		 
       
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		 Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		 Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
				User user = (User) session.get(User.class, userId);
		
				/*Criteria c=session.createCriteria(Integrallevel.class);
				c.add(Restrictions.gt("integralUp",user.getIntegral() ));
				c.add(Restrictions.and(Restrictions.lt("integralDown",user.getIntegral())));*/

				Integrallevel integrallevel =get_user_integral_level(user);
				
				 float integralPercent= (float)user.getIntegral()/integrallevel.getIntegralUp();   
				 DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
				 String integralPercentStr = df.format(integralPercent);//返回的是String类型 
				
				Criteria c=session.createCriteria(Integrallevel.class);
				c=session.createCriteria(User.class);
				c.add(Restrictions.eq("deleteFlag",0));
				c.addOrder(Order.desc("integral"));
				
				List<User> userList = c.list();
				
				Map<String, Object> rankingsMapMy = new LinkedHashMap<String, Object>();
				List<Map<String, Object>> rankingsList = new ArrayList<Map<String,Object>>();
				
				rankingsMapMy.put("ranking",userList.indexOf(user)+1);
				rankingsMapMy.put("userInfo", user);
				rankingsMapMy.put("integral",user.getIntegral());
				rankingsMapMy.put("integralLevel", integrallevel.getLevel());
				rankingsMapMy.put("nextLevel", integrallevel.getNextLevel());
				rankingsMapMy.put("integralPercent", integralPercentStr);
				rankingsList.add(rankingsMapMy);
				
				for(int i=0;i<3;i++)
				{
					Map<String, Object> rankingsMap = new LinkedHashMap<String, Object>();
					rankingsMap.put("userInfo", userList.get(i));
					rankingsMap.put("ranking", i+1);
					rankingsMap.put("integralLevel",get_user_integral_level(user).getLevel());
					rankingsMap.put("nextLevel",get_user_integral_level(user).getNextLevel());
					rankingsList.add(rankingsMap);
				}			
				
				
				
				contentMap.put("rankings", rankingsList);
				contentMap.put("myRanking", rankingsMapMy);
				
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
	
	@GET   
	@Path("/{userId}/softVersion")  
	public String get_softVersion(@PathParam ("userId") int userId,@Context HttpServletRequest request) {  
		
		 
       
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		 Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		 Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		 contentMap.put("updateMsg","已经是最新版本");
		 returnMap.put("ipUpdate","0");
		 returnMap.put("updateUrl","itms-services://?action=download-manifest&url=https://git.oschina.net/WendleXu/innoShare/raw/master/innoShare_test.plist");
		try{
			String phoneType = request.getParameter("phoneType");
			
			Criteria c=session.createCriteria(SoftVersion.class);
			c.add(Restrictions.and(Restrictions.eq("phoneType",request.getParameter("phoneType"))));
			c.addOrder(Order.desc("currentVersion"));
			
			List<SoftVersion> softVersionList = c.list();
			
			if(!request.getParameter("currentVersion").equals(softVersionList.get(0).getCurrentVersion())) 
			{
				contentMap.put("updateMsg",softVersionList.get(0).getUpdateMsg());
				contentMap.put("updateUrl",softVersionList.get(0).getUpdateUrl());
				contentMap.put("updateMsg","");
				returnMap.put("ipUpdate","1");
			}
			   
		}finally{
			
			session.getTransaction().commit();
		}
		
		returnMap.put("message","sucess");
		returnMap.put("code","1");
		returnMap.put("content", contentMap);
		
		JSONObject jsonObject = new JSONObject();
		 
		   jsonObject = JSONObject.fromObject(returnMap);

		
		
	    return  jsonObject.toString();  
	}  
	
	
	public Integrallevel get_user_integral_level(User user)
	{
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		//session.beginTransaction();
		Criteria c=session.createCriteria(Integrallevel.class);
		c.add(Restrictions.ge("integralUp",user.getIntegral() ));
		c.add(Restrictions.and(Restrictions.le("integralDown",user.getIntegral())));

		
		Integrallevel integrallevel =(Integrallevel) c.list().get(0);
		return integrallevel;
		
	}
	
}
