package com.yonyou.base.dao;

import java.util.List;  

public interface BaseDao<T> {  
      
    public void add(T entity) throws Exception;  
  
    public void delete(T entity) throws Exception;  
  
    public void update(T entity) throws Exception;  
  
    public T findById(Integer id) throws Exception;  
    /* 
     * �õ���startIndex��ʼ��СΪpageSize���б� 
     */  
    public List<T> getPageList(int startIndex , int pageSize) throws Exception;     
    /* 
     * �õ����� 
     */  
    public long getAmount();  
}  