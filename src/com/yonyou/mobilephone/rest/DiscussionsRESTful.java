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
import org.springframework.web.bind.annotation.RequestBody;




























import com.yonyou.discussion.IdeaDiscussion;
import com.yonyou.idea.form.Idea;
import com.yonyou.idea.form.IdeaTagMap;
import com.yonyou.image.form.IdeaImage;
import com.yonyou.tag.form.Person;
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

		try{
				
				IdeaDiscussion ideaDiscussion = new IdeaDiscussion();
				ideaDiscussion.setDiscussionContent(m.get("discussionContent").toString());
				ideaDiscussion.setCreateTime(new Date());
				
				User user = (User) session.get(User.class, Integer.parseInt(m.get("creatorID").toString()));
				
				ideaDiscussion.setCreator(user);
				ideaDiscussion.setDeleteFag(0);
				ideaDiscussion.setFatherID(Integer.parseInt(m.get("fatherID").toString()));
				ideaDiscussion.setFaviourCount(0);
				
				Idea idea = (Idea) session.get(Idea.class, Integer.parseInt(m.get("ideaID").toString()));
				ideaDiscussion.setIdea(idea);
			
				session.save(ideaDiscussion);
				
		        
		        
		        returnMap.put("message","success");
				returnMap.put("code","1");
				   
		}finally{
				
				session.getTransaction().commit();
		}
			
		JSONObject jsonObject = JSONObject.fromObject(returnMap);
		
	    return  jsonObject.toString();  
	}
	
	@GET  
	@Path("/idea/{ideaID}")  
	public String getDiscussionBy_ideaID(@PathParam ("ideaID") int ideaID,@Context HttpServletRequest request) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();

		try{
				Criteria c=session.createCriteria(IdeaDiscussion.class);				
				IdeaDiscussion ideaDiscussion = new IdeaDiscussion();
				
				Idea idea = (Idea)session.get(Idea.class, ideaID);
				
				c.add(Restrictions.eq("idea",idea));
				c.add(Restrictions.eq("fatherID",0));
				c.addOrder(Order.desc("createTime"));
				
				List<Map<String,Object>> ideaAllDiscussionList = new ArrayList<Map<String,Object>>();
				List<IdeaDiscussion> ideaTopDiscussionList= new ArrayList<IdeaDiscussion>();
				ideaTopDiscussionList=c.list();
							
				System.out.println(ideaTopDiscussionList.size());
				
				
				
				for(int i=0;i<ideaTopDiscussionList.size();i++)
				{
					Map<String,Object> ideaDiscussionMap = new LinkedHashMap<String,Object>();
					Map<String,Object> ideaFatherDiscussionMap = new LinkedHashMap<String,Object>();
					System.out.println(ideaTopDiscussionList.get(i).getDiscussionId()+"@@@@@@@@@@@@@@@");
					ideaFatherDiscussionMap.put("father", ideaTopDiscussionList.get(i));
					
					c=session.createCriteria(IdeaDiscussion.class);
					c.add(Restrictions.eq("idea",idea));
					c.add(Restrictions.eq("fatherID",ideaTopDiscussionList.get(i).getDiscussionId()));
					c.addOrder(Order.desc("createTime"));
					
					List<IdeaDiscussion> ideaSonDiscussionList = new ArrayList<IdeaDiscussion>();
					ideaSonDiscussionList=c.list();
					//System.out.println(c.list().size()+"@@@@@@@@@@@"+ideaTopDiscussionList.get(i).getFatherID());
					
					ideaFatherDiscussionMap.put("son", ideaSonDiscussionList);
					
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
	@Path("/{discussionID}")  
	public String update_Discussion_By_discussionsID(@PathParam ("discussionID") int discussionID,@RequestBody Map<String,Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();

		try{
				
		        IdeaDiscussion ideaDiscussion = (IdeaDiscussion) session.get(IdeaDiscussion.class, discussionID);
		        User updator = (User) session.get(User.class, (Integer)m.get("updatorID"));
		        
		   
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
	@Path("/{discussionID}")  
	public String delete_Discussion_By_discussionsID(@PathParam ("discussionID") int discussionID,@RequestBody Map<String,Object> m) {  
  
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();

		try{
				
		        IdeaDiscussion ideaDiscussion = (IdeaDiscussion) session.get(IdeaDiscussion.class, discussionID);
		        User updator = (User) session.get(User.class, (Integer)m.get("updatorID"));
		        
		   
		        ideaDiscussion.setUpdator(updator);
		        ideaDiscussion.setLastUpdateTime(new Date());
		        ideaDiscussion.setDeleteFag(1);
		        
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
}
