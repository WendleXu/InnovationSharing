package com.yonyou.base.service;

import java.util.List;

import com.yonyou.base.form.BaseForm;

public interface BaseService <E extends BaseForm> {
	
	E get(long id);
	void create(E obj);
	void delete(E obj);
	void update(E obj);
	int getTotalCount();
	List<E> getPage(int startIndex,int count);
	List<E> getAll();

}
