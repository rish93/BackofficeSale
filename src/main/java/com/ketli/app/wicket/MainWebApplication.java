package com.ketli.app.wicket;

import java.util.Properties;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//import com.ketli.app.model.Login;
import com.ketli.app.util.HibernateUtil;


public class MainWebApplication  extends WebApplication{

	 public MainWebApplication() {
	    }
	
	 @Override
		public void init() {

			super.init();

			try {
				Configuration cfg = new Configuration();
				Properties p = new Properties();
				//load properties file
				p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
				cfg.setProperties(p);
				// build session factory
				cfg.addAnnotatedClass(Login.class);
				SessionFactory factory = cfg.buildSessionFactory();
				// get session
				Session session = factory.openSession();
				System.out.println("begin session "+session.isConnected());
				// close session
				
//				Login l = new Login();
//				
//				l.setUsername("prateek");
//				l.setPassword("123");
//				session.save(l);
//				session.close();
//				System.out.println("close session "+session.isConnected());
				
	
				
				
				//HibernateUtil.initializeHibernate(); // Initialize Hibernate
				//HibernateUtil.beginTransaction();

				//setAuthStrategy();

//				getApplicationSettings().setPageExpiredErrorPage(Login.class);
//				getApplicationSettings().setInternalErrorPage(Login.class);
//				getApplicationSettings().setAccessDeniedPage(Login.class);

			//	getDebugSettings().setComponentUseCheck(false);// Allows u to use
																// same component
																// more than once on
																// same wicket page

				initFriendlyUrls();

				// Strip wicket tags
			//	getMarkupSettings().setStripWicketTags(true);

				// init velocity
				
//				
//				try {
//					Velocity.init();
//					SlackUtil.init();
//					telegramBotRegistryMap = TelegramUtil.init(telegramBotRegistryMap);
//				}catch (Exception ex){
//					logger.error("Unexpected Error",ex);
//				}
				// Load base data... uncomment this
				// new ApplicationDataUtil().loadBasedata();

			//	setupRequestListener();

//			} catch (Throwable e) {
//				logger.error("*** Application Startup Failed ***", e);
//				throw new RuntimeException(e);
//			}
		}
		catch(Exception e) {
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {			//	HibernateUtil.commitTransaction();
			
			}
}

	 
	 
	@Override
	public Class getHomePage() {
		// TODO Auto-generated method stub
		return Login.class;
	}

	private void initFriendlyUrls() {
		
		mountPage("/login", Login.class);
		mountPage("/home", HelloWorld.class);
		//mountPage("/login", Login.class);
	
	}
	
	//	private void setupRequestListener() {
//		getRequestCycleListeners().add(new IRequestCycleListener() {
//
//			@Override
//			public void onBeginRequest(RequestCycle cycle) {
//				Transaction tr = HibernateUtil.getHibernateSessionNoSecurity().getTransaction();
//				if (tr == null || !tr.isActive()) {
//					HibernateUtil.getHibernateSessionNoSecurity().beginTransaction();
//				}
//
//				// tenant intercepter (if not admin)
//				UserSession session = UserSession.get();
//				if (session != null && session.getUser() != null && !session.getUser().isSystemAdmin()) {
//					TenantIdThreadLocal.set(session.getUser().getTenant().getId());
//				} else {
//					TenantIdThreadLocal.set(null);
//				}
//			}
//
//			@Override
//			public void onEndRequest(RequestCycle cycle) {
//				Transaction tr = HibernateUtil.getHibernateSessionNoSecurity().getTransaction();
//				if (tr != null && tr.isActive() && !tr.wasCommitted() && !tr.wasRolledBack()) {
//
//					HibernateUtil.getHibernateSessionNoSecurity().flush();
//
//					tr.commit();
//				}
//			}
//
//			@Override
//			public IRequestHandler onException(RequestCycle cycle, Exception ex) {
//
//				Transaction tr = HibernateUtil.getHibernateSessionNoSecurity().getTransaction();
//				if (tr != null && tr.isActive() && !tr.wasCommitted() && !tr.wasRolledBack()) {
//					tr.rollback();
//				}
//
//				return null;
//			}
//
//			@Override
//			public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler,
//					Exception exception) {
//			}
//
//			@Override
//			public void onRequestHandlerExecuted(RequestCycle cycle, IRequestHandler handler) {
//			}
//
//			@Override
//			public void onUrlMapped(RequestCycle cycle, IRequestHandler handler, Url url) {
//			}
//
//			@Override
//			public void onDetach(RequestCycle cycle) {
//			}
//
//			@Override
//			public void onRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler) {
//			}
//
//			@Override
//			public void onRequestHandlerScheduled(RequestCycle cycle, IRequestHandler handler) {
//			}
//
//		});

	/**git status
	 * git init
	 * git add .
	 * git commit -a -m ""
	 * git remote add origin git@github.com:User/UserRepo.git
	 * git remote set-url origin git@github.com:User/UserRepo.git
	 * git push -u origin master 
	 * */
	
}

