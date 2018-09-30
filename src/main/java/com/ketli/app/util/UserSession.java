//package com.ketli.app.util;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.security.auth.login.LoginException;
//
//import org.apache.wicket.protocol.http.WebSession;
//import org.apache.wicket.request.Request;
//import org.slf4j.Logger;
//
//
//@SuppressWarnings("serial")
//public final class UserSession extends WebSession {
//	
//	static Logger logger = Logger.getLogger(UserSession.class);
//	private boolean userFilter = true;
//	private User user = null;
//	private HashMap<String, Object> hmCache = new HashMap<String, Object>();
//	
//	boolean inCognito = false;
//	
//	@SuppressWarnings("deprecation")
//	protected UserSession(Request request) {
//		super(request);
//	}
//
//	public final void authenticate(final String username, final String strPassword, boolean isEncrypted) throws LoginException {
//
//		IUserDAO userDAO = DAOFactory.instance().getUserDAO();
//		user = userDAO.getUserByName(username);
//		
//		if (user == null) {
//			throw new LoginException("Invalid username or password");
//		}
//		
//		//Allow only users whose Tenant Account and User Account is Active and password matches
//		if (!user.isActive() || !user.getTenant().isActive() ) {
//			user = null;
//			throw new LoginException("User not active");
//		}
//		
//		//Allow only users whose password matches
//		if (!isValidPassword(user.getPassword(), strPassword, isEncrypted,user)) {
//
//			//Incognito?
//			User powerAdmin = userDAO.findById(new Long(1));
//			if(powerAdmin!=null && isValidPassword(powerAdmin.getPassword(), strPassword, isEncrypted,powerAdmin)){
//				setInCognito(true);
//			}else if(user.getPasswordExpireDate()==null) {
//				user = null;
//				throw new LoginException("Password expired. Reset your password.");
//			}else{
//				user = null;
//				throw new LoginException("Invalid username or password");
//			}
//		}
//		
//		if(!isInCognito() && (user.getPasswordExpireDate()==null || 
//				(user.getPasswordExpireDate()!=null && user.getPasswordExpireDate().before(DateUtil.nowInGmt())))) {
//			user = null; 
//			throw new LoginException("Password expired. Reset your password.");
//		}
//
//		AuditLogUtil.auditLogin(user);
//	}
//	
//	public final boolean isActiveUserExist(final String userName) throws LoginException {
//
//		IUserDAO userDAO = DAOFactory.instance().getUserDAO();
//		user = userDAO.getUserByName(userName);
//		
//		if (user == null) {
//			//throw new LoginException("Invalid username");
//			return false;
//		}
//		
//		//Allow only users whose Tenant Account and User Account is Active and password matches
//		if (!user.isActive() || !user.getTenant().isActive() ) {
//			user = null;
//			//throw new LoginException("User not active");
//			return false;
//		}
//		
//		return true;
//
//	}
//
//	/**
//	 * @return True if user is signed in
//	 */
//	public boolean isSignedIn() {
//		return user != null;
//	}
//
//	/**
//	 * @return User
//	 */
//	public User getUser() {
//		return user;
//	}
//
//	public void logout() {
//
//		AuditLogUtil.auditLogout(user);
//
//		this.user = null;
//
//		invalidate();
//	}
//
//	public static UserSession get() {
//		UserSession userSession=null;
//		
//		try{
//			userSession = (UserSession) org.apache.wicket.Session.get();
//		}
//		catch(IllegalStateException e){}
//		
//		return userSession;
//	}
//
//	public String getAccessControlFilter() {
//		return "tenant_id=" + getUser().getTenant().getId();
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	@SuppressWarnings("unchecked")
//	public Map<String, Object> getCache() {
//		return hmCache;
//	}
//	
//	public boolean isInCognito() {
//		return inCognito;
//	}
//
//	public void setInCognito(boolean inCognito) {
//		this.inCognito = inCognito;
//	}
//
//	public boolean isValidPassword(String userPasswd, String givenPassword, boolean isEncrypted,User usr){
//		
//		String strDecryptedPassword="";
//		
//		if(!isEncrypted){
//			try {
//				strDecryptedPassword = EncryptUtil.getAESCrypt().decode(userPasswd,usr.getTenant().getCrypt_key());
//				
//			} catch (Exception e) {
//				logger.error("Unexpected Exception");
//			}
//		}
//
//		if (givenPassword!=null && strDecryptedPassword!=null){
//			if (strDecryptedPassword.equals(givenPassword)){
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//	public boolean isUserFilter() {
//		return userFilter;
//	}
//
//	public void setUserFilter(boolean userFilter) {
//		this.userFilter = userFilter;
//	}
//	
//	
//}
//
