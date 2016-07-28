package com.yonyou.base.dao;

import java.util.List;  

public interface BaseDao<T> {  
      
    public void add(T entity) throws Exception;  
  
    public void delete(T entity) throws Exception;  
  
    public void update(T entity) throws Exception;  
  
    public T findById(Integer id) throws Exception;  
    /* 
     * 得到从startIndex开始大小为pageSize的列表 
     */  
    public List<T> getPageList(int startIndex , int pageSize) throws Exception;     
    /* 
     * 得到总数 
     */  
    public long getAmount();  
}  