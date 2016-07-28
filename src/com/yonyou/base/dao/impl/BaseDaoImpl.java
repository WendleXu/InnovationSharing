package com.yonyou.base.dao.impl;

import java.lang.reflect.ParameterizedType;  
import java.util.List;  
  
import javax.annotation.Resource;  
  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
  
import com.yonyou.base.dao.BaseDao;  
  
  
public class BaseDaoImpl<T> implements BaseDao<T> {  
  
    private Class<T> entityClass;  
    private String hql;  
    @Resource  
    private SessionFactory sessionFactory;  
      
      
    public Session getSession(){  
        return sessionFactory.getCurrentSession();  
    }  
      
      
    @SuppressWarnings("unchecked")  
    public BaseDaoImpl() {  
        //通过反射获取泛型传过来的类的类对象  
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass()  
                .getGenericSuperclass()).getActualTypeArguments()[0];  
        this.hql = "from " + this.entityClass.getName();  
    }  
      
    @Override  
    public void add(Object entity) {  
        this.getSession().save(entity);  
    }  
  
    @Override  
    public void delete(Object entity) {  
        this.getSession().delete(entity);  
    }  
  
    @Override  
    public void update(Object entity) {  
        this.getSession().update(entity);  
    }  
      
    @Override  
    public T findById(Integer id) {  
        @SuppressWarnings("unchecked")  
        T result = (T) this.getSession().get(entityClass,id);  
        return result;  
    }  
  
  
    @Override  
    public List<T> getPageList(int startIndex, int pageSize) {  
        // TODO Auto-generated method stub  
        @SuppressWarnings("unchecked")  
        List<T> list = this.getSession().createQuery(hql).setFirstResult(startIndex).setMaxResults(pageSize).list();  
        System.out.println(hql);  
        return list;  
    }  
  
  
    @Override  
    public long getAmount() {  
        String sql = "select count(*) from "+ this.entityClass.getName();  
        long count =  (Long) this.getSession().createQuery(sql).uniqueResult() ;  
        return count;  
    }  
      
}  
