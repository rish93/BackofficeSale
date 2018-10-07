package com.ketli.app.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.ketli.app.model.Login;


@Repository
public class HibernateUtil {
	private static Configuration configuration;

	@Autowired
	private static SessionFactory sessionFactory ;
	
	@Autowired
	private static Session session;
	private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

public HibernateUtil() {
	
}
	
	
	/**
	 * Wrapper Method to get Hibernate Session
	 * 
	 * @return
	 * @throws IOException 
	 */
	
	public static SessionFactory getSessionFactory() throws IOException
	{
		Configuration cfg = new Configuration();
		Properties p = new Properties();
		//load properties file
		p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		cfg.setProperties(p);
		// build session factory
		cfg.addAnnotatedClass(Login.class);
		SessionFactory factory = cfg.buildSessionFactory();
		sessionFactory=factory;
		return factory;
	}
	
	
	public static Session getSession() {
		
		session=sessionFactory.openSession();
		return session;
	}
	
	
	
	
	
	public static Session getHibernateSession() {
		Session session = getHibernateSessionNoSecurity();
		//Tenant Filter requires an Active Transaction
		if (!session.getTransaction().isActive()){
			session.beginTransaction();
		}
		
		//apply tenant filter
		
		return session;
	}


	
	public static void removeTenantFilter() {
		getHibernateSession().disableFilter("Tenant_Security");
		getHibernateSession().disableFilter("Tenant_Owner_Security");
	}

	//dont use for general use
	public static Session getHibernateSessionNoSecurity() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	/**
	 * Creates new Session via DataBinder directly from Hibernate. To be used if
	 * Active Session Context not available. Care should be taken to disconnect
	 * after use.
	 * 
	 * @return
	 */
	public static Session getNewHibernateSession() {
		Session s = sessionFactory.openSession();
		return s;
	}

	public static void beginTransaction() {
		if (!getHibernateSessionNoSecurity().getTransaction().isActive()) {
			getHibernateSessionNoSecurity().beginTransaction();
		}
	}

	public static void commitTransaction() {
		if (getHibernateSessionNoSecurity().getTransaction().isActive()) {
			getHibernateSessionNoSecurity().flush();
			getHibernateSessionNoSecurity().getTransaction().commit();
		}
	}

	public static void rollbackTransaction() {
		if (getHibernateSessionNoSecurity().getTransaction().isActive()) {
			getHibernateSessionNoSecurity().getTransaction().rollback();
		}
	}

	/**
	 * 
	 * @param cfg
	 */
	public static void buildSessionFactory(Configuration cfg) {
		logger.debug("Rebuilding the SessionFactory from given Configuration");
		
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		} 
		else {
			logger.debug("Holding SessionFactory in static variable");
			sessionFactory = cfg.buildSessionFactory();
		}
		configuration = cfg;
	}

	public static void initializeHibernate() throws Throwable {
		initializeHibernate("application.properties");		
	}

	public static void initializeHibernate(String cfgFile) throws Throwable {
		
		if(sessionFactory!=null){
			logger.warn("Hibernate Initialize Already Done");
			return;
		}
		
		try {
			logger.debug("Initializing Hibernate");

			configuration = new Configuration();
			Properties p = new Properties();
			//load properties file
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(cfgFile));
			configuration.setProperties(p);
			
			
			configuration.configure(cfgFile);
			//
			configuration.addAnnotatedClass(Login.class);
					
			buildSessionFactory(configuration);

			logger.debug("Hibernate initialized, call HibernateUtil.getSessionFactory()");
		} 
		catch (Throwable ex) {
			logger.error("Building SessionFactory failed.", ex);
			throw ex;
		}
	}
	
	/**
	 * Closes the current SessionFactory and releases all resources.
	 * <p>
	 * The only other method that can be called on HibernateUtil after this one
	 * is rebuildSessionFactory(Configuration).
	 */
//	public static void shutdown() {
//		logger.debug("Shutting down Hibernate");
//		// Close caches and connection pools
//		getSessionFactory().close();
//
//		// Clear static variables
//		sessionFactory = null;
//	}

	/**
	 * Returns the Hibernate configuration that was used to build the
	 * SessionFactory.
	 * 
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Returns the global SessionFactory either from a static variable or a JNDI
	 * lookup.
	 * 
	 * @return SessionFactory
	 */
//	public static SessionFactory getSessionFactory() {
//		if (sessionFactory == null) {
//			buildSessionFactory(configuration);
//		}
//		return sessionFactory;
//	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (Exception ignore) {
			}
		}
	}

	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (Exception ignore) {
			}
		}
	}

	public static void close(Session dbSession) {
		if (dbSession != null) {
			dbSession.close();
		}
	}


}
