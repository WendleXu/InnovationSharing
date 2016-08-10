package com.yonyou.mobilephone.rest;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.yonyou.integral.form.IntegralLevel;
import com.yonyou.integral.service.impl.IntegralServiceImpl;
import com.yonyou.record.form.IdeaFaviourRecord;
import com.yonyou.tag.form.Tag;
import com.yonyou.user.form.User;
import com.yonyou.util.HibernateSessionFactoryUtil;
import com.yonyou.util.JsonDateValueProcessor;

@Path("/mobilephone/ideas")
public class IdeasRESTful {
	
	

	@GET   
	@Path("/{ideaId}")  
	public String get_idea_detail(@PathParam ("ideaId") int ideaId) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
			
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> userInfoMap = new LinkedHashMap<String, Object>();
		try{
			
			Criteria c=session.createCriteria(Idea.class);
			
			Idea idea = (Idea) session.get(Idea.class, ideaId);
			
			userInfoMap.put("creatorId",idea.getUser().getUserId());
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
		   config.setExcludes(new String[]{"user","ideaTagMaps","ideaDiscussions","ideaImages"}) ;
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
			
			Idea idea = (Idea) session.get(Idea.class, Integer.parseInt(request.getParameter("ideaId")) );
			
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
			
			
			List<Idea> ideaList = new ArrayList<Idea>();
			if(request.getParameter("country")!=null && !request.getParameter("country").equals(""))
			{
				Criteria c=session.createCriteria(Idea.class);
				c.add(Restrictions.eq("country",request.getParameter("country")));//eq是等于，gt是大于，lt是小于,or是或
				c.add(Restrictions.eq("deleteFlag", 0));
				c.addOrder(Order.desc("createTime"));
				
				
				c.setProjection(Projections.rowCount());
				c.setProjection(null);

				c.setFirstResult((10)*(Integer.parseInt(request.getParameter("pageNo"))-1));
				c.setMaxResults(10);
				ideaList=c.list();
			}else if(request.getParameter("tagId")!=null && !request.getParameter("tagId").equals(""))
			{
				List<IdeaTagMap> ideaTagMaps = new ArrayList<IdeaTagMap>();
				Criteria c=session.createCriteria(IdeaTagMap.class);
				c.add(Restrictions.eq("tag", session.get(Tag.class, Integer.parseInt(request.getParameter("tagId")))));
				
				c.add(Restrictions.eq("deleteFlag", 0));
				c.addOrder(Order.desc("createTime"));
				
				
				c.setProjection(Projections.rowCount());
				c.setProjection(null);

				c.setFirstResult((10)*(Integer.parseInt(request.getParameter("pageNo"))-1));
				c.setMaxResults(10);
				ideaTagMaps=c.list();
				
				for(int i=0;i<ideaTagMaps.size();i++)
				{
					ideaList.add(ideaTagMaps.get(i).getIdea());
				}
			}else{
				Criteria c=session.createCriteria(Idea.class);
				//c.add(Restrictions.eq("country",request.getParameter("country")));//eq是等于，gt是大于，lt是小于,or是或
				c.add(Restrictions.eq("deleteFlag", 0));
				c.addOrder(Order.desc("createTime"));
				
				
				c.setProjection(Projections.rowCount());
				c.setProjection(null);

				c.setFirstResult((10)*(Integer.parseInt(request.getParameter("pageNo"))-1));
				c.setMaxResults(10);
				ideaList=c.list();
			}
			
			
			
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


			String hql="select idea.country,count(idea.ideaId) from Idea idea group by country";
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
				tagsMap.put("tagId", tag.getTagId());
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
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> tooltipMap = new LinkedHashMap<String, Object>();

		try{
				
				Idea idea = new Idea();
				User user = new User();

				user = (User) session.get(User.class, Integer.parseInt(m.get("userId").toString()));

				idea.setUser(user);
				idea.setIdeaTitle(m.get("title").toString());
				idea.setCountry(m.get("country").toString());
				idea.setDescription(m.get("description").toString());
				idea.setTitleImgId(Integer.parseInt(m.get("titleImgId").toString()));
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
			
				idea.setCreateTime(cal.getTime());
				idea.setTheYear(cal.get(Calendar.YEAR));
				idea.setTheMonth(cal.get(Calendar.MONTH));
				idea.setTheWeek(cal.get(Calendar.WEEK_OF_YEAR));
				idea.setTheDay(cal.get(Calendar.DAY_OF_MONTH));
				
				
				idea.setFaviourCount(0);
				idea.setReadCount(0);
				idea.setCommentCount(0);	
				idea.setDeleteFlag(0);
				
				
			
				session.save(idea);
				
		        JSONArray jsonArray = JSONArray.fromObject(m.get("tags"));   
		        List list = (List)JSONArray.toList(jsonArray, Tag.class);
		        Iterator it = list.iterator();  
		        while(it.hasNext()){  
		            Tag tag = (Tag)it.next();   
		            System.out.println(tag.getTagId());
		            IdeaTagMap ideaTagMap = new IdeaTagMap();
		            ideaTagMap.setIdea(idea);
		            ideaTagMap.setDeleteFlag(0);
		            ideaTagMap.setCreateTime(new Date());
		            ideaTagMap.setTag((Tag) session.get(Tag.class, tag.getTagId()));
		            session.save(ideaTagMap);
		        }  
		        
		        IntegralLevel integralOldLevel = IntegralServiceImpl.get_user_integral_level(user);
		        //添加idea，用户积分+10
				user.setIntegral(user.getIntegral()+10);
				IntegralLevel integralNewLevel = IntegralServiceImpl.get_user_integral_level(user);
				
				if(!integralOldLevel.getLevel().equals(integralNewLevel.getLevel()))
				{
					tooltipMap.put("isLevelUp",1);
					tooltipMap.put("currentLevel", integralNewLevel.getLevel());
				}else{
					tooltipMap.put("isLevelUp",0);
					tooltipMap.put("currentLevel", integralNewLevel.getLevel());
				}
				
				session.update(user);
				//添加idea，提示积分增加
				tooltipMap.put("tooltip","+10");
		        
		        returnMap.put("message","success");
				returnMap.put("code","1");
				returnMap.put("content", tooltipMap);
				   
		}finally{
				
				session.getTransaction().commit();
				 
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
	@PUT
	@Path("/{ideaId}/detail")
	public String update_idea_details(@RequestBody Map<String,Object> m) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Idea idea = (Idea) session.get(Idea.class,  (Integer)m.get("ideaId"));
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
	@Path("/{ideaId}/faviour")
	public String update_idea_faviour(@RequestBody Map<String,Object> m) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> tooltipMap = new LinkedHashMap<String, Object>();
		try{
			
			Idea idea = (Idea) session.get(Idea.class,Integer.parseInt(m.get("ideaId").toString()));
			IdeaFaviourRecord ideaFaviourRecord=null;
			if(m.get("updateWay").equals("add")){
				idea.setFaviourCount(idea.getFaviourCount()+1);
				
				Criteria c=session.createCriteria(IdeaFaviourRecord.class);
				c.add(Restrictions.eq("ideaId", Integer.parseInt(m.get("ideaId").toString())));
				c.add(Restrictions.eq("userId", Integer.parseInt(m.get("userId").toString())));
				List <IdeaFaviourRecord> ideaFaviourRecords = c.list();
				if(ideaFaviourRecords.size()>0){
					ideaFaviourRecord = (IdeaFaviourRecord) c.list().get(0);
					ideaFaviourRecord.setDeleteFlag(0);
					ideaFaviourRecord.setLastUpdateTime(new Date());
					ideaFaviourRecord.setIsFavour(1);
					
				}else{
					
					ideaFaviourRecord = new IdeaFaviourRecord();
					
					ideaFaviourRecord.setDeleteFlag(0);
					ideaFaviourRecord.setCreateTime(new Date());
					ideaFaviourRecord.setIsFavour(1);
					ideaFaviourRecord.setIdeaId(Integer.parseInt(m.get("ideaId").toString()));
					ideaFaviourRecord.setUserId(Integer.parseInt(m.get("userId").toString()));
										
					//idea点赞，提示积分+1
					tooltipMap.put("tooltip","+1");
					
					User user = (User) session.get(User.class, Integer.parseInt(m.get("userId").toString()));
					
					IntegralLevel integralOldLevel = IntegralServiceImpl.get_user_integral_level(user);
					//idea点赞，用户积分+1
					user.setIntegral(user.getIntegral()+1);		
					IntegralLevel integralNewLevel = IntegralServiceImpl.get_user_integral_level(user);
					
					if(!integralOldLevel.getLevel().equals(integralNewLevel.getLevel()))
					{
						tooltipMap.put("isLevelUp",1);
						tooltipMap.put("currentLevel", integralNewLevel.getLevel());
					}else{
						tooltipMap.put("isLevelUp",0);
						tooltipMap.put("currentLevel", integralNewLevel.getLevel());
					}
					
					session.update(user);
					
				}
				
				
				
			}else{
				if(idea.getFaviourCount()>0){
					idea.setFaviourCount(idea.getFaviourCount()-1);
				}				
				Criteria c=session.createCriteria(IdeaFaviourRecord.class);
				c.add(Restrictions.eq("ideaId",Integer.parseInt(m.get("ideaId").toString())));
				c.add(Restrictions.eq("userId",Integer.parseInt(m.get("userId").toString())));
				ideaFaviourRecord = (IdeaFaviourRecord) c.list().get(0);
				ideaFaviourRecord.setDeleteFlag(1);
				ideaFaviourRecord.setLastUpdateTime(new Date());
				ideaFaviourRecord.setIsFavour(0);
			}
			
			session.merge(ideaFaviourRecord);
			
			session.update(idea);
			
			returnMap.put("message","success");
			returnMap.put("code","1");
			returnMap.put("content", tooltipMap);
			
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		
		JSONObject jsonObject = new JSONObject();
		jsonObject= JSONObject.fromObject(returnMap);

	    return  jsonObject.toString();  
	}
	
	@GET
	@Path("/{ideaId}/faviour")
	public String get_isFaviour_info(@Context HttpServletRequest request) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		
		try{
			
			Idea idea = (Idea) session.get(Idea.class,Integer.parseInt(request.getParameter("ideaId")));
			
			Criteria c=session.createCriteria(IdeaFaviourRecord.class);
			c.add(Restrictions.eq("ideaId", Integer.parseInt(request.getParameter("ideaId").toString())));
			c.add(Restrictions.eq("userId", Integer.parseInt(request.getParameter("userId").toString())));
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
	@Path("/{ideaId}/readNum")
	public String update_idea_readNum(@RequestBody Map<String,Object> m) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		try{
			
			Idea idea = (Idea) session.get(Idea.class,  (Integer)m.get("ideaId"));
			
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
	
	//更新idea,添加新的内容
	@POST   
	@Path("/renewal")  
	public String add_idea_renewal(@RequestBody Map<String, Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> tooltipMap = new LinkedHashMap<String, Object>();

		try{
				
				Idea idea = new Idea();
				User user = new User();
				IdeaUpdateRecord  ideaUpdateRecord = new IdeaUpdateRecord();

				user = (User) session.get(User.class, Integer.parseInt(m.get("userId").toString()));
				idea = (Idea) session.get(Idea.class, Integer.parseInt(m.get("ideaId").toString()));
				
				ideaUpdateRecord.setCreateTime(new Date());
				ideaUpdateRecord.setDeleteFlag(0);
				ideaUpdateRecord.setIdea(idea);
				ideaUpdateRecord.setUpdateContent(m.get("updateContent").toString());
				ideaUpdateRecord.setUpdateDescription(m.get("updateDescription").toString());
				ideaUpdateRecord.setUpdateTitle(m.get("updateTitle").toString());
				ideaUpdateRecord.setUpdateType(m.get("updateType").toString());
				ideaUpdateRecord.setUser(user);
				
				session.save(ideaUpdateRecord);
		        
				IntegralLevel integralOldLevel = IntegralServiceImpl.get_user_integral_level(user);
				 //补充添加idea，用户积分+5
				user.setIntegral(user.getIntegral()+5);			
				IntegralLevel integralNewLevel = IntegralServiceImpl.get_user_integral_level(user);
				
				if(!integralOldLevel.getLevel().equals(integralNewLevel.getLevel()))
				{
					tooltipMap.put("isLevelUp",1);
					tooltipMap.put("currentLevel", integralNewLevel.getLevel());
				}else{
					tooltipMap.put("isLevelUp",0);
					tooltipMap.put("currentLevel", integralNewLevel.getLevel());
				}
				
				session.update(user);
				//补充添加idea,提示积分增加
				tooltipMap.put("tooltip","+5");
				
				
				
		        returnMap.put("message","success");
				returnMap.put("code","1");
				returnMap.put("content", tooltipMap);
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
}
