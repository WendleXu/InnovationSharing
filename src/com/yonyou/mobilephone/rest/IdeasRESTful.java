package com.yonyou.mobilephone.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;






import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.RequestBody;

@Path("/mobilephone/ideas")
public class IdeasRESTful {

	@GET   
	@Path("/distribution")  
	public String get_ideas_distribution() {  
  
		/*Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();*/
			
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> return_map = new LinkedHashMap<String, Object>();
		Map<String, Object> content_map = new LinkedHashMap<String, Object>();
		try{
				
			
			/* //from后面是对象，不是表名
			String hql="from UserInfo as user where user."+m.get("login_type")+"=:account and password=:password";//使用命名参数，推荐使用，易读。
			Query query=session.createQuery(hql);
			query.setString("account", m.get("account"));
			query.setString("password", m.get("password"));

			@SuppressWarnings("unchecked")
			List<UserInfo> list=query.list();*/


			return_map.put("message","success");
			return_map.put("code","1");
			List<Map<String, Object>> idea_layout_list = new ArrayList<Map<String, Object>>();
			
			Map<String, Object> idea_layout_map01 = new LinkedHashMap<String, Object>();
			idea_layout_map01.put("country", "China");
			idea_layout_map01.put("count", 210);

			Map<String, Object> idea_layout_map02 = new LinkedHashMap<String, Object>();
			idea_layout_map02.put("country", "Germany");
			idea_layout_map02.put("count", 380);

			idea_layout_list.add(idea_layout_map01);
			idea_layout_list.add(idea_layout_map02);
			content_map.put("distribution",idea_layout_list);

			List<Map<String, Object>> tags_list = new ArrayList<Map<String, Object>>();
			Map<String, Object> tags_map01 = new LinkedHashMap<String, Object>();
			tags_map01.put("tag", "AR");

			Map<String, Object> tags_map02 = new LinkedHashMap<String, Object>();
			tags_map02.put("tag", "VR");

			Map<String, Object> tags_map03 = new LinkedHashMap<String, Object>();
			tags_map03.put("tag", "Digital");

			Map<String, Object> tags_map04 = new LinkedHashMap<String, Object>();
			tags_map04.put("tag", "IOT");

			tags_list.add(tags_map01);
			tags_list.add(tags_map02);
			tags_list.add(tags_map03);
			tags_list.add(tags_map04);

			content_map.put("tags",tags_list);
			
			return_map.put("content", content_map);
			
				   
		}finally{
				
				//session.getTransaction().commit();
		}
			
		JSONObject jsonObject = JSONObject.fromObject(return_map);
		
	    return  jsonObject.toString();  
	}
	
	
}
