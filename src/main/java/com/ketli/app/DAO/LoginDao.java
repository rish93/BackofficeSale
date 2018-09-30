package com.ketli.app.DAO;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ketli.app.model.Login;

@Repository
public class LoginDao {

	
	@Autowired
	static Session session;
	
	public static Login findByName(String names, String password) {
		   return (Login) session.createCriteria(Login.class)
				   .add(Restrictions.in("password", password))
				   .add(Restrictions.in("name", names));
		}
	
	
}

