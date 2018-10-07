package com.ketli.app.DAO;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ketli.app.model.Login;
import com.ketli.app.util.HibernateUtil;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;

@Repository
public class LoginDao {

	@Autowired
	static HibernateUtil hUtil;	

	LoginDao(){
		//SessionFactory factory=hUtil.getSessionFactory();
		
	}
	
//	@Autowired
//	SessionFactory factory;
	
	@Autowired
	static Session session ;
	
	public static Login findByName(String names, String password) {
		System.out.println(session);
List<Login> loginRes=		 (List<Login>) hUtil.getSession().createCriteria(Login.class)
		   .add(Restrictions.eq("password", password))
		   .add(Restrictions.eq("username", names)).list();
		
		return loginRes!=null && loginRes.size()>0 ? loginRes.get(0): null;
		}
	
	
}

