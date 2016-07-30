package com.yonyou.mobilephone.rest;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.PropertyFilter;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestBody;




















import com.yonyou.idea.form.Idea;
import com.yonyou.idea.form.IdeaTagMap;
import com.yonyou.tag.form.Person;
import com.yonyou.tag.form.Tag;
import com.yonyou.user.form.User;
import com.yonyou.util.DateJsonValueProcessor;
import com.yonyou.util.HibernateSessionFactoryUtil;

@Path("/mobilephone/ideas")
public class IdeasRESTful {

	@GET   
	@Path("/{ideaID}")  
	public String get_idea_detail(@PathParam ("ideaID") int ideaID) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
			
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Criteria c=session.createCriteria(Idea.class);
			
			Idea idea = (Idea) session.get(Idea.class, ideaID);
			
			
			contentMap.put("ideaDetal",idea);
			contentMap.put("userID",idea.getUser().getUserId());
			
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			
			returnMap.put("content", contentMap);
			
				   
		}finally{
				
				//session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		  try {
		   JsonConfig config = new JsonConfig(); 
		   config.setExcludes(new String[]{"user","ideaTagMaps"}) ;
		   jsonObject = JSONObject.fromObject(returnMap,config);

		  } catch (Exception e) {
		  
		  }

		
	    return  jsonObject.toString();  
	}
	
	@GET   
	@Path("/")  
	public String get_ideas(@Context HttpServletRequest request) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
			
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Criteria c=session.createCriteria(Idea.class);
			
			if(request.getParameter("country")!=null && !request.getParameter("country").equals(""))
			{
				c.add(Restrictions.eq("country",request.getParameter("country")));//eq是等于，gt是大于，lt是小于,or是或
			}
			
			if(request.getParameter("tagID")!=null && !request.getParameter("tagID").equals(""))
			{
				c.add(Restrictions.eq("tagID", request.getParameter("tagID")));
			}
			
			
			c.setProjection(Projections.rowCount());
			int totalRecord=Integer.valueOf(c.uniqueResult().toString());
			c.setProjection(null);

			c.setFirstResult((10)*(Integer.parseInt(request.getParameter("pageNo"))-1));
			c.setMaxResults(10);
			List<Idea> ideaList=c.list();
			
			
			contentMap.put("ideas",ideaList);
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			
			returnMap.put("content", contentMap);
			
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		  try {
		   JsonConfig config = new JsonConfig(); 
		   config.setExcludes(new String[]{"user","ideaTagMaps"});
		   jsonObject = JSONObject.fromObject(returnMap,config);

		  } catch (Exception e) {
		  
		  }

		
	    return  jsonObject.toString();  
	}
	
	@GET   
	@Path("/distribution")  
	public String get_ideas_distribution() {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
			
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
				
			
			/* //from后面是对象，不是表名
			String hql="from UserInfo as user where user."+m.get("login_type")+"=:account and password=:password";//使用命名参数，推荐使用，易读。
			Query query=session.createQuery(hql);
			query.setString("account", m.get("account"));
			query.setString("password", m.get("password"));

			@SuppressWarnings("unchecked")
			List<UserInfo> list=query.list();*/


			String hql="select idea.country,count(idea.ideaID) from Idea idea group by country";
            Query query=session.createQuery(hql);
            @SuppressWarnings("unchecked")
            List<Object[]> list=query.list();
            List<Map<String, Object>> ideaLayoutList = new ArrayList<Map<String, Object>>();
            for(Object[]objs:list){
            	Map<String, Object> ideaLayoutMap = new LinkedHashMap<String, Object>();
                
                ideaLayoutMap.put("country",objs[0]);
                ideaLayoutMap.put("count",objs[1]);
                ideaLayoutList.add(ideaLayoutMap);
            }

			contentMap.put("distribution",ideaLayoutList);

			//from后面是对象，不是表名
			String hql02="from Tag as tag";//使用命名参数，推荐使用，易读。
			Query query02=session.createQuery(hql02);
			   
			List<Tag> tags=query02.list();
			List<Map<String, Object>> tagList = new ArrayList<Map<String, Object>>();
			
			for(Tag tag:tags){
				Map<String, Object> tagsMap = new LinkedHashMap<String, Object>();
				tagsMap.put("tagName", tag.getTagName());
				tagsMap.put("tagID", tag.getTagID());
				tagList.add(tagsMap);
				
			}
			
			

			contentMap.put("tags",tagList);
			
			returnMap.put("content", contentMap);
			
			
				   
		}finally{
				
				session.getTransaction().commit();
				returnMap.put("message","success");
				returnMap.put("code","1");
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
	@POST   
	@Path("/")  
	public String add_ideas(@RequestBody Map<String, Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();

		try{
				
				Idea idea = new Idea();
				User user = new User();

				user = (User) session.get(User.class, (Integer)m.get("userID"));

				idea.setUser(user);
				idea.setIdeaTitle(m.get("title").toString());
				idea.setCountry(m.get("country").toString());
				idea.setDescription(m.get("description").toString());
				idea.setTitleImgID((Integer) m.get("titleImgID"));
				idea.setCreateTime(new Date());
			
				session.save(idea);
				
		        JSONArray jsonArray = JSONArray.fromObject(m.get("tags"));  
		        System.out.println(jsonArray);  
		        List list = (List)JSONArray.toList(jsonArray, Tag.class);
		        Iterator it = list.iterator();  
		        while(it.hasNext()){  
		            Tag tag = (Tag)it.next();   
		            System.out.println(tag.getTagID());
		            IdeaTagMap ideaTagMap = new IdeaTagMap();
		            ideaTagMap.setIdea(idea);
		            ideaTagMap.setDeleteFlag(0);
		            ideaTagMap.setCreateTime(new Date());
		            ideaTagMap.setTag((Tag) session.get(Tag.class, tag.getTagID()));
		            session.save(ideaTagMap);
		        }  
		        
		        returnMap.put("message","success");
				returnMap.put("code","1");
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
	
}
