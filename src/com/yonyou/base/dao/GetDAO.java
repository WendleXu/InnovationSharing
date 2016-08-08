package com.yonyou.base.dao;

import java.util.List;

import com.yonyou.base.form.BaseForm;

public interface GetDAO<E extends BaseForm>  {

	public E findById(Integer id) throws Exception;  
    /* 
     * 得到从startIndex开始大小为pageSize的列表 
     */  
    public List<E> getPageList(int startIndex , int pageSize) throws Exception;     
    /* 
     * 得到总数 
     */  
    public long getAmount(); 
	
}
