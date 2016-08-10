package com.yonyou.mobilephone.rest;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestBody;

import com.mchange.v2.codegen.bean.BeangenUtils;
import com.yonyou.discussion.form.IdeaDiscussion;
import com.yonyou.discussion.form.IdeaDiscussionCopy;
import com.yonyou.idea.form.Idea;
import com.yonyou.idea.form.IdeaTagMap;
import com.yonyou.image.form.IdeaImage;
import com.yonyou.integral.form.IntegralLevel;
import com.yonyou.integral.service.impl.IntegralServiceImpl;
import com.yonyou.record.form.DiscussionFaviourRecord;
import com.yonyou.record.form.IdeaFaviourRecord;
import com.yonyou.tag.form.Tag;
import com.yonyou.user.form.User;
import com.yonyou.util.HibernateSessionFactoryUtil;
import com.yonyou.util.JsonDateValueProcessor;

@Path("/mobilephone/discussions")
public class DiscussionsRESTful {
	
	@POST   
	@Path("/")  
	public String add_discussion(@RequestBody Map<String, Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> tooltipMap = new LinkedHashMap<String, Object>();

		try{
				
				IdeaDiscussion ideaDiscussion = new IdeaDiscussion();
				ideaDiscussion.setDiscussionContent(m.get("discussionContent").toString());
				ideaDiscussion.setCreateTime(new Date());
				
				User user = (User) session.get(User.class, Integer.parseInt(m.get("creatorId").toString()));
				
				ideaDiscussion.setCreator(user);
				ideaDiscussion.setDeleteFag(0);
				ideaDiscussion.setFatherId(Integer.parseInt(m.get("fatherId").toString()));
				ideaDiscussion.setFaviourCount(0);				
				Idea idea = (Idea) session.get(Idea.class, Integer.parseInt(m.get("ideaId").toString()));				
				ideaDiscussion.setIdea(idea);			
				session.save(ideaDiscussion);
								
				//更新idea的评论数
				idea.setCommentCount(idea.getCommentCount()+1);
				session.update(idea);
				
				IntegralLevel integralOldLevel = IntegralServiceImpl.get_user_integral_level(user);
				//添加评论，用户积分+1
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
				//新增评论提示积分增加
				tooltipMap.put("tooltip","+1");
		        
		        returnMap.put("message","success");
				returnMap.put("code","1");
				returnMap.put("content", tooltipMap);
				   
		}finally{
				
				session.getTransaction().commit();
				 
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
	@GET  
	@Path("/idea/{ideaId}")  
	public String get_Discussion_By_ideaId(@PathParam ("ideaId") int ideaId,@Context HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();

		try{
				Criteria c=session.createCriteria(IdeaDiscussion.class);				
				IdeaDiscussion ideaDiscussion = new IdeaDiscussion();
				
				Idea idea = (Idea)session.get(Idea.class, ideaId);
				
				c.add(Restrictions.eq("idea",idea));
				c.add(Restrictions.eq("fatherId",0));
				c.addOrder(Order.asc("createTime"));
				
				List<Map<String,Object>> ideaAllDiscussionList = new ArrayList<Map<String,Object>>();
				List<IdeaDiscussion> ideaTopDiscussionList= new ArrayList<IdeaDiscussion>();
				ideaTopDiscussionList=c.list();
								
				
				for(int i=0;i<ideaTopDiscussionList.size();i++)
				{
					Map<String,Object> ideaDiscussionMap = new LinkedHashMap<String,Object>();
					Map<String,Object> ideaFatherDiscussionMap = new LinkedHashMap<String,Object>();
					IdeaDiscussionCopy ideaDiscussionCopyFather = new IdeaDiscussionCopy();					
					
					BeanUtils.copyProperties(ideaDiscussionCopyFather, ideaTopDiscussionList.get(i));					
					ideaDiscussionCopyFather.setCreatorPortraitUrl(ideaTopDiscussionList.get(i).getCreator().getPortraitUrl());
					ideaDiscussionCopyFather.setCreatorName(ideaTopDiscussionList.get(i).getCreator().getUserName());
					ideaDiscussionCopyFather.setCreatorId(ideaTopDiscussionList.get(i).getCreator().getUserId());
					ideaDiscussionCopyFather.setIdeaId(ideaTopDiscussionList.get(i).getIdea().getIdeaId());
					
					c=session.createCriteria(DiscussionFaviourRecord.class);
					c.add(Restrictions.eq("discussionId",ideaDiscussionCopyFather.getDiscussionId()));
					c.add(Restrictions.eq("userId",Integer.parseInt(request.getParameter("userId"))));
					c.add(Restrictions.eq("deleteFlag",0));
					c.add(Restrictions.eq("isFavour",1));
					
					if(c.list().size()>0)
					{
						ideaDiscussionCopyFather.setIsFavour(1);
					}
					
					ideaFatherDiscussionMap.put("father", ideaDiscussionCopyFather);
					
					c=session.createCriteria(IdeaDiscussion.class);
					c.add(Restrictions.eq("idea",idea));
					c.add(Restrictions.eq("fatherId",ideaTopDiscussionList.get(i).getDiscussionId()));
					c.addOrder(Order.asc("createTime"));
					
					List<IdeaDiscussion> ideaSonDiscussionList = new ArrayList<IdeaDiscussion>();
					List<IdeaDiscussionCopy> ideaSonDiscussionListCopy = new ArrayList<IdeaDiscussionCopy>();
					ideaSonDiscussionList=c.list();
					for(int j=0;j<ideaSonDiscussionList.size();j++)
					{
						IdeaDiscussionCopy ideaDiscussionCopySon = new IdeaDiscussionCopy();
						BeanUtils.copyProperties(ideaDiscussionCopySon, ideaSonDiscussionList.get(j));					
						ideaDiscussionCopySon.setCreatorPortraitUrl(ideaSonDiscussionList.get(j).getCreator().getPortraitUrl());
						ideaDiscussionCopySon.setCreatorName(ideaSonDiscussionList.get(j).getCreator().getUserName());
						ideaDiscussionCopySon.setCreatorId(ideaSonDiscussionList.get(j).getCreator().getUserId());
						ideaDiscussionCopySon.setIdeaId(ideaSonDiscussionList.get(j).getIdea().getIdeaId());
						ideaSonDiscussionListCopy.add(ideaDiscussionCopySon);
					}
					
					
					
					
					ideaFatherDiscussionMap.put("son", ideaSonDiscussionListCopy);
					
					ideaDiscussionMap.put("discussion",ideaFatherDiscussionMap);
					
					ideaAllDiscussionList.add(ideaDiscussionMap);
				}

				
				contentMap.put("discussions",ideaAllDiscussionList);
		        
		        returnMap.put("message","success");
				returnMap.put("code","1");
				returnMap.put("content", contentMap);
				   
		}finally{
				
				session.getTransaction().commit();
				 
		}
			
		//JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
		JSONObject jsonObject = new JSONObject();
		  try {
		   JsonConfig jsonConfig = new JsonConfig(); 
		   jsonConfig.setExcludes(new String[]{"idea","creator","updator"});
		   jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
		   jsonObject = JSONObject.fromObject(returnMap,jsonConfig);

		  } catch (Exception e) {
		  
		  }
		
	    return  jsonObject.toString();  
	}
	
	@PUT  
	@Path("/{discussionId}")  
	public String update_Discussion_By_discussionsId(@PathParam ("discussionId") int discussionId,@RequestBody Map<String,Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();

		try{
				
		        IdeaDiscussion ideaDiscussion = (IdeaDiscussion) session.get(IdeaDiscussion.class, discussionId);
		        User updator = (User) session.get(User.class, Integer.parseInt(m.get("updatorId").toString()));
		        
		   
		        ideaDiscussion.setUpdator(updator);
		        ideaDiscussion.setLastUpdateTime(new Date());
		        ideaDiscussion.setDiscussionContent(m.get("modifyContent").toString());
		        
		        session.save(ideaDiscussion);
		        
		       
				   
		}finally{
				
				session.getTransaction().commit(); 
				returnMap.put("message","success");
				returnMap.put("code","1");
				returnMap.put("content", contentMap);
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
	@DELETE  
	@Path("/{discussionId}")  
	public String delete_Discussion_By_discussionsId(@PathParam ("discussionId") int discussionId,@RequestBody Map<String,Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();

		try{
				
		        IdeaDiscussion ideaDiscussion = (IdeaDiscussion) session.get(IdeaDiscussion.class, discussionId);
		        User updator = (User) session.get(User.class, (Integer)m.get("updatorId"));
		        
		   
		        ideaDiscussion.setUpdator(updator);
		        ideaDiscussion.setLastUpdateTime(new Date());
		        ideaDiscussion.setDeleteFag(1);
		        
		        session.save(ideaDiscussion);
		        
		        Idea idea = (Idea) session.get(Idea.class, Integer.parseInt(m.get("ideaId").toString()));
		      //减少idea的评论数
				idea.setCommentCount(idea.getCommentCount()+1);
				session.save(idea);
		        
				   
		}finally{
				
				session.getTransaction().commit(); 
				returnMap.put("message","success");
				returnMap.put("code","1");
				returnMap.put("content", contentMap);
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
	@PUT
	@Path("/{discussionId}/faviour")
	public String update_discussion_faviour(@RequestBody Map<String,Object> m) {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> tooltipMap = new LinkedHashMap<String, Object>();
		try{
			
			IdeaDiscussion ideaDiscussion = (IdeaDiscussion) session.get(IdeaDiscussion.class,  Integer.parseInt(m.get("discussionId").toString()));
			
			if(m.get("updateWay").equals("add"))
			{
				
				Criteria c=session.createCriteria(DiscussionFaviourRecord.class);
				c.add(Restrictions.eq("discussionId", Integer.parseInt(m.get("discussionId").toString())));
				c.add(Restrictions.eq("userId", Integer.parseInt(m.get("userId").toString())));
				c.add(Restrictions.eq("discussionType", m.get("discussionType").toString()));
				List <DiscussionFaviourRecord> discussionFaviourRecords = c.list();
				if(discussionFaviourRecords.size()>0)
				{
					DiscussionFaviourRecord discussionFaviourRecord = (DiscussionFaviourRecord) c.list().get(0);
					discussionFaviourRecord.setDeleteFlag(0);
					discussionFaviourRecord.setLastUpdateTime(new Date());
					discussionFaviourRecord.setIsFavour(1);
					discussionFaviourRecord.setDiscussionType(m.get("discussionType").toString());
					session.update(discussionFaviourRecord);
				}else{
					
					DiscussionFaviourRecord discussionFaviourRecord = new DiscussionFaviourRecord();
					
					discussionFaviourRecord.setDeleteFlag(0);
					discussionFaviourRecord.setCreateTime(new Date());
					discussionFaviourRecord.setIsFavour(1);
					discussionFaviourRecord.setDiscussionId(Integer.parseInt(m.get("discussionId").toString()));
					discussionFaviourRecord.setUserId(Integer.parseInt(m.get("userId").toString()));
					discussionFaviourRecord.setDiscussionType(m.get("discussionType").toString());
					session.save(discussionFaviourRecord);
					
					User user = (User) session.get(User.class, Integer.parseInt(m.get("userId").toString()));
					
					IntegralLevel integralOldLevel = IntegralServiceImpl.get_user_integral_level(user);
					//评论点赞，用户积分+1
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
					
					//评论点赞，提示积分增加
					tooltipMap.put("tooltip","+1");		
					session.update(user);
					
				}
				//评论的点赞数+1
				ideaDiscussion.setFaviourCount(ideaDiscussion.getFaviourCount()+1);
				
			}else{
				
				//评论的点赞数-1
				if(ideaDiscussion.getFaviourCount()>0){
					ideaDiscussion.setFaviourCount(ideaDiscussion.getFaviourCount()-1);
				}				
				Criteria c=session.createCriteria(DiscussionFaviourRecord.class);
				c.add(Restrictions.eq("userId", Integer.parseInt(m.get("userId").toString())));
				c.add(Restrictions.eq("discussionId",  Integer.parseInt(m.get("discussionId").toString())));
				c.add(Restrictions.eq("discussionType", m.get("discussionType").toString()));
				DiscussionFaviourRecord discussionFaviourRecord = (DiscussionFaviourRecord) c.list().get(0);
				discussionFaviourRecord.setDeleteFlag(1);
				discussionFaviourRecord.setLastUpdateTime(new Date());
				discussionFaviourRecord.setIsFavour(0);
				session.update(discussionFaviourRecord);

			}
			
			session.merge(ideaDiscussion);
			
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
	
}
