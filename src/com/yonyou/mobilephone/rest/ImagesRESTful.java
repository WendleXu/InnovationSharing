package com.yonyou.mobilephone.rest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestBody;

import com.yonyou.idea.form.Idea;
import com.yonyou.image.form.IdeaImage;
import com.yonyou.user.form.User;
import com.yonyou.util.HibernateSessionFactoryUtil;

@Path("/mobilephone/images") 
public class ImagesRESTful {

	@POST   
	@Path("/userPortraits")  
	public String add_user_portrait(@HeaderParam ("userId") int userId,@HeaderParam ("fileType") String fileType,@Context HttpServletRequest request) throws IOException {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		Map<String, Object> return_map = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		
		try{
			
			InputStream in = request.getInputStream();

			String filePath = "";
			filePath = System.getProperty("csc2.root")+"images/userPortraits/"+userId+"."+fileType;

			OutputStream bos = new FileOutputStream(filePath);// 建立一个上传文件的输出流
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			bos.close();
			in.close();
			
			String img_url_str = "http://" + request.getServerName() //服务器地址  
                    + ":"   
                    + request.getServerPort()           //端口号  
                    + request.getContextPath()      //项目名称  
                	+"images/userPortraits/"+userId+"."+fileType;
			
			User user = (User) session.get(User.class, userId);
			user.setPortraitUrl(img_url_str);
			
			session.update(user);
			session.getTransaction().commit();
			
			contentMap.put("imgType", "userPortrait");
			contentMap.put("userId", userId);
			contentMap.put("imgUrl", img_url_str);
			
		    return_map.put("message","sucess");
		    return_map.put("code","1");
		    return_map.put("content",contentMap);
		    

			   
		}finally{
			
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(return_map);
		
	    return  jsonObject.toString();  
	}  
	
	
	@POST   
	@Path("/ideaDescriptions")  
	public String add_idea_Descriptions(@HeaderParam ("ideaId") int ideaId,@HeaderParam ("fileType") String fileType,@Context HttpServletRequest request) throws IOException {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		
		Map<String, Object> return_map = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		
		try{
			
			InputStream in = request.getInputStream();

			String filePath = "";
			filePath = System.getProperty("csc2.root")+"images/ideaDescriptions/"+ideaId+"."+fileType;

			OutputStream bos = new FileOutputStream(filePath);// 建立一个上传文件的输出流
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			bos.close();
			in.close();
			
			String img_url_str = "http://" + request.getServerName() //服务器地址  
                    + ":"   
                    + request.getServerPort()           //端口号  
                    + request.getContextPath()      //项目名称  
                	+"images/userPortraits/"+ideaId+"."+fileType;
			
			Idea idea = (Idea) session.get(Idea.class, ideaId);
			
			IdeaImage ideaImage = new IdeaImage();
			ideaImage.setCreateTime(new Date());
			ideaImage.setDeleteFlag(0);
			ideaImage.setIdea(idea);
			ideaImage.setImgUrl(img_url_str);
			
			
			session.save(ideaImage);
			session.getTransaction().commit();
			
			contentMap.put("imgType", "ideaDescriptions");
			contentMap.put("ideaId", ideaId);
			contentMap.put("imgUrl", img_url_str);
			contentMap.put("ideaImgId", ideaImage.getIdeaImageId());
			
		    return_map.put("message","sucess");
		    return_map.put("code","1");
		    return_map.put("content",contentMap);
		    

			   
		}finally{
			
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(return_map);
		
	    return  jsonObject.toString();  
	}  
	
	
	/*@POST   
	@Path("/project")  
	public String add_image(@HeaderParam ("img_group") String img_group,@HeaderParam ("img_type") String img_type,@HeaderParam ("related_id") int related_id,@HeaderParam ("file_type") String file_type,@Context HttpServletRequest request) throws IOException {  
		
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		session.beginTransaction();
		Map<String, Object> return_map = new LinkedHashMap<String, Object>();
		try{
			
			InputStream in = request.getInputStream();

			//System.out.println("@@@@@@@@"+System.getProperty("csc2.root"));
			String filePath = "";
			filePath = System.getProperty("csc2.root")+"images/"+img_group+"/"+img_type+"/"+related_id+(new Date())+"."+file_type;

			OutputStream bos = new FileOutputStream(filePath);// 建立一个上传文件的输出流
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			bos.close();
			in.close();
			
			String img_url_str = "http://" + request.getServerName() //服务器地址  
                    + ":"   
                    + request.getServerPort()           //端口号  
                    + request.getContextPath()      //项目名称  
                	+"/images/"+img_group+"/"+img_type+"/"+related_id+"."+file_type;
			
			User user = (User) session.get(User.class, related_id);
			user.setPortraitUrl(img_url_str);
			
			session.update(user);
			session.getTransaction().commit();
			
		    return_map.put("message","sucess");
		    return_map.put("code","1");

			   
		}finally{
			
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(return_map);
		
	    return  jsonObject.toString();  
	}  */
}
