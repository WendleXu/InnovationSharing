package com.yonyou.base.dao;

import java.util.List;

import com.yonyou.base.form.BaseForm;

public interface GetDAO<E extends BaseForm>  {

	public E findById(Integer id) throws Exception;  
    /* 
     * �õ���startIndex��ʼ��СΪpageSize���б� 
     */  
    public List<E> getPageList(int startIndex , int pageSize) throws Exception;     
    /* 
     * �õ����� 
     */  
    public long getAmount(); 
	
}
