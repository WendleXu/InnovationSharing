package com.yonyou.integral.service.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.yonyou.integral.form.IntegralLevel;
import com.yonyou.user.form.User;
import com.yonyou.util.HibernateSessionFactoryUtil;

public class IntegralServiceImpl {

	public static IntegralLevel get_user_integral_level(User user)
	{
		Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();  
		//session.beginTransaction();
		Criteria c=session.createCriteria(IntegralLevel.class);
		c.add(Restrictions.ge("integralUp",user.getIntegral() ));
		c.add(Restrictions.and(Restrictions.le("integralDown",user.getIntegral())));		
		IntegralLevel integrallevel =(IntegralLevel) c.list().get(0);		
		return integrallevel;
		
		
		
	}
	
}
