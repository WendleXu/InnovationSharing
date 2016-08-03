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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.criteria.expression.function.LengthFunction;
import org.springframework.web.bind.annotation.RequestBody;






import com.yonyou.idea.form.Idea;
import com.yonyou.idea.form.IdeaTagMap;
import com.yonyou.idea.form.IdeaUpdateRecord;
import com.yonyou.image.form.IdeaImage;
import com.yonyou.record.form.IdeaFaviourRecord;
import com.yonyou.tag.form.Person;
import com.yonyou.tag.form.Tag;
import com.yonyou.user.form.User;
import com.yonyou.util.HibernateSessionFactoryUtil;
import com.yonyou.util.JsonDateValueProcessor;

@Path("/mobilephone/ideas")
public class IdeasRESTful {
	
	

	@GET   
	@Path("/{ideaID}")  
	public String get_idea_detail(@PathParam ("ideaID") int ideaID) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
			
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> userInfoMap = new LinkedHashMap<String, Object>();
		try{
			
			Criteria c=session.createCriteria(Idea.class);
			
			Idea idea = (Idea) session.get(Idea.class, ideaID);
			
			userInfoMap.put("creatorID",idea.getUser().getUserId());
			userInfoMap.put("creatorName",idea.getUser().getUserName());
			userInfoMap.put("creatorPortrait",idea.getUser().getPortraitUrl());
			userInfoMap.put("country",idea.getUser().getCountry());
			contentMap.put("userInfo", userInfoMap);
			
			contentMap.put("ideaDetal",idea);
			
			
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			
			returnMap.put("content", contentMap);
			
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		  try {
		   JsonConfig config = new JsonConfig(); 
		   config.setExcludes(new String[]{"user","ideaTagMaps","ideaDiscussions","ideaImages","country"}) ;
		   config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
		   jsonObject = JSONObject.fromObject(returnMap,config);

		  } catch (Exception e) {
		  
		  }

		
	    return  jsonObject.toString();  
	}
	
	@GET   
	@Path("/renewals")  
	public String get_idea_renewal_list(@Context HttpServletRequest request) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
			
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Idea idea = (Idea) session.get(Idea.class, Integer.parseInt(request.getParameter("ideaID")) );
			
			Criteria c=session.createCriteria(IdeaUpdateRecord.class);
			c.add(Restrictions.eq("idea",idea));
			c.addOrder(Order.desc("createTime"));
			
			List<IdeaUpdateRecord> ideaUpdateList=c.list();
			contentMap.put("ideas",ideaUpdateList);
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			
			returnMap.put("content", contentMap);
			
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		  try {
		   JsonConfig config = new JsonConfig(); 
		   config.setExcludes(new String[]{"user","idea"}) ;
		   config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
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
			
			c.addOrder(Order.desc("createTime"));
			
			c.setProjection(Projections.rowCount());
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
		   JsonConfig jsonConfig = new JsonConfig(); 
		   jsonConfig.setExcludes(new String[]{"user","ideaTagMaps","ideaImages","ideaDiscussions"});
		   jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
		   jsonObject = JSONObject.fromObject(returnMap,jsonConfig);

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
	public String add_idea(@RequestBody Map<String, Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();

		try{
				
				Idea idea = new Idea();
				User user = new User();

				user = (User) session.get(User.class, Integer.parseInt(m.get("userID").toString()));

				idea.setUser(user);
				idea.setIdeaTitle(m.get("title").toString());
				idea.setCountry(m.get("country").toString());
				idea.setDescription(m.get("description").toString());
				idea.setTitleImgID(Integer.parseInt(m.get("titleImgID").toString()));
				idea.setCreateTime(new Date());
				idea.setFaviourCount(0);
				idea.setReadCount(0);
				idea.setCommentCount(0);	
				idea.setDeleteFlag(0);
			
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
	
	@PUT
	@Path("/{ideaID}/detail")
	public String update_idea_details(@RequestBody Map<String,Object> m) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Idea idea = (Idea) session.get(Idea.class,  (Integer)m.get("ideaID"));
			idea.setIdeaTitle(m.get("ideaTitle").toString());
			idea.setDescription(m.get("description").toString());
			idea.setDetail(m.get("detail").toString());
			
			session.save(idea);
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			returnMap.put("content", "");
			
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		jsonObject= JSONObject.fromObject(returnMap);

	    return  jsonObject.toString();  
	}
	
	@PUT
	@Path("/{ideaID}/faviour")
	public String update_idea_faviour(@RequestBody Map<String,Object> m) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Idea idea = (Idea) session.get(Idea.class,Integer.parseInt(m.get("ideaID").toString()));
			
			if(m.get("updateWay").equals("add")){
				idea.setFaviourCount(idea.getFaviourCount()+1);
				
				Criteria c=session.createCriteria(IdeaFaviourRecord.class);
				c.add(Restrictions.eq("ideaID", Integer.parseInt(m.get("ideaID").toString())));
				c.add(Restrictions.eq("userID", Integer.parseInt(m.get("userID").toString())));
				List <IdeaFaviourRecord> ideaFaviourRecords = c.list();
				if(ideaFaviourRecords.size()>0){
					IdeaFaviourRecord ideaFaviourRecord = (IdeaFaviourRecord) c.list().get(0);
					ideaFaviourRecord.setDeleteFlag(0);
					ideaFaviourRecord.setLastUpdateTime(new Date());
					ideaFaviourRecord.setIsFavour(1);
					session.update(ideaFaviourRecord);
				}else{
					
					IdeaFaviourRecord ideaFaviourRecord = new IdeaFaviourRecord();
					
					ideaFaviourRecord.setDeleteFlag(0);
					ideaFaviourRecord.setCreateTime(new Date());
					ideaFaviourRecord.setIsFavour(1);
					ideaFaviourRecord.setIdeaID(Integer.parseInt(m.get("ideaID").toString()));
					ideaFaviourRecord.setUserID(Integer.parseInt(m.get("userID").toString()));
					session.save(ideaFaviourRecord);
					
				}
				
				
				
			}else{
				if(idea.getFaviourCount()>0){
					idea.setFaviourCount(idea.getFaviourCount()-1);
				}				
				Criteria c=session.createCriteria(Idea.class);
				c.add(Restrictions.eq("ideaID",Integer.parseInt(m.get("ideaID").toString())));
				c.add(Restrictions.eq("userID",Integer.parseInt(m.get("userID").toString())));
				IdeaFaviourRecord ideaFaviourRecord = (IdeaFaviourRecord) c.list().get(0);
				ideaFaviourRecord.setDeleteFlag(1);
				ideaFaviourRecord.setLastUpdateTime(new Date());
				ideaFaviourRecord.setIsFavour(0);
				session.update(ideaFaviourRecord);
			}
			
			
			session.save(idea);
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			returnMap.put("content", "");
			
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		jsonObject= JSONObject.fromObject(returnMap);

	    return  jsonObject.toString();  
	}
	
	@GET
	@Path("/{ideaID}/faviour")
	public String get_isFaviour_info(@Context HttpServletRequest request) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		
		try{
			
			Idea idea = (Idea) session.get(Idea.class,Integer.parseInt(request.getParameter("ideaID")));
			
			Criteria c=session.createCriteria(IdeaFaviourRecord.class);
			c.add(Restrictions.eq("ideaID", Integer.parseInt(request.getParameter("ideaID").toString())));
			c.add(Restrictions.eq("userID", Integer.parseInt(request.getParameter("userID").toString())));
			List <IdeaFaviourRecord> ideaFaviourRecords = c.list();
			if(ideaFaviourRecords.size()>0 && ideaFaviourRecords.get(0).getDeleteFlag()==0 && ideaFaviourRecords.get(0).getIsFavour()==1)
			{
				contentMap.put("isFaviour", 1);
			}else{
				contentMap.put("isFaviour", 0);
			}			
			returnMap.put("message","success");
			returnMap.put("code","1");
			returnMap.put("content", contentMap);				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		jsonObject= JSONObject.fromObject(returnMap);

	    return  jsonObject.toString();  
	}
	
	@PUT
	@Path("/{ideaID}/readNum")
	public String update_idea_readNum(@RequestBody Map<String,Object> m) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Idea idea = (Idea) session.get(Idea.class,  (Integer)m.get("ideaID"));
			
			if(m.get("updateWay").equals("add"))
			{
				idea.setReadCount(idea.getReadCount()+1);
			}else{
				if(idea.getFaviourCount()>0)
				{
					idea.setReadCount(idea.getReadCount()-1);
				}				
			}
			
			
			session.save(idea);
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			returnMap.put("content", "");
			
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		jsonObject= JSONObject.fromObject(returnMap);

	    return  jsonObject.toString();  
	}
	
	@POST   
	@Path("/renewal")  
	public String add_idea_renewal(@RequestBody Map<String, Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();

		try{
				
				Idea idea = new Idea();
				User user = new User();
				IdeaUpdateRecord  ideaUpdateRecord = new IdeaUpdateRecord();

				user = (User) session.get(User.class, Integer.parseInt(m.get("userID").toString()));
				idea = (Idea) session.get(Idea.class, Integer.parseInt(m.get("ideaID").toString()));
				
				ideaUpdateRecord.setCreateTime(new Date());
				ideaUpdateRecord.setDeleteFlag(0);
				ideaUpdateRecord.setIdea(idea);
				ideaUpdateRecord.setUpdateContent(m.get("updateContent").toString());
				ideaUpdateRecord.setUpdateDescription(m.get("updateDescription").toString());
				ideaUpdateRecord.setUpdateTitle(m.get("updateTitle").toString());
				ideaUpdateRecord.setUpdateType(m.get("updateType").toString());
				ideaUpdateRecord.setUser(user);
				
				session.save(ideaUpdateRecord);
		        
		        returnMap.put("message","success");
				returnMap.put("code","1");
				returnMap.put("content", "");
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
}
