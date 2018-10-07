package com.ketli.app.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class KetliHome extends WebPage {

	private StringValue username;
	
	public KetliHome(PageParameters p) {
		username=	p.get("username");
	
	
		Model<String> strMdl = Model.of("Welcome "+username);
		Label msg = new Label("labelUsername", strMdl);
		msg.setOutputMarkupId(true);
		add(msg);
	
	}
	
	
	
	
}
