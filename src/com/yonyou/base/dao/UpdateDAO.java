package com.yonyou.base.dao;

import com.yonyou.base.form.BaseForm;

public interface UpdateDAO<E extends BaseForm> {

	 public void add(E entity) throws Exception;  
	  
	 public void delete(E entity) throws Exception;  
	  
	 public void update(E entity) throws Exception;  
	   
	
}
