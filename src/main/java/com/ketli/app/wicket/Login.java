package com.ketli.app.wicket;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.ketli.app.model.*;
import com.ketli.app.DAO.LoginDao;
import com.ketli.app.util.HibernateUtil;

public class Login  extends WebPage{

	@Autowired
	Session session;
	
//	@Autowired
//	Login login;
	
	public Login() {
     
		
		final TextField<String> username = new TextField<String>("username",
				Model.of(""));
		username.setRequired(true);
		
		
		final TextField<String> password = new TextField<String>("password",
				Model.of(""));
		password.setRequired(true);
	//	username.add(new UsernameValidator());

		Form<?> form = new Form<Void>("userForm") {

			@Override
			protected void onSubmit() {

				final String usernameValue = username.getModelObject();
				final String pswd=password.getModelObject();
				PageParameters pageParameters = new PageParameters();
				
			//	Login loginrep=(Login) session.byId(Login.class);
				session =HibernateUtil.getSession();
				if(LoginDao.findByName(usernameValue, pswd)!=null) {
					pageParameters.add("username", usernameValue);
					pageParameters.add("password", pswd);
					
					setResponsePage(KetliHome.class,pageParameters);

				}
				else
				{
					setResponsePage(Login.class,pageParameters);
				}
				
			}

		};
	
		
		add(form);
		form.add(username);
		form.add(password);
		
	}	
	
	
}

