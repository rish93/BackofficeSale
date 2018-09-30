package com.ketli.app.wicket;


import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HelloWorld extends WebPage{
	
	public HelloWorld() {
	        add(new Label("message", "Hello World!"));
	     
	        add(new Link("link") {
	        	@Override
	        	public void onClick() {
//	        		PageParameters pageParameters = new PageParameters();
//	        		pageParameters.add("foo", "foo");
//	        		pageParameters.add("bar", "bar");
	        		setResponsePage(LinkCounter.class);
	        	}

				@Override
				public MarkupContainer setDefaultModel(IModel model) {
					// TODO Auto-generated method stub
					return null;
				}

//				@Override
//				public MarkupContainer setDefaultModel(IModel model) {
//					// TODO Auto-generated method stub
//					return null;
//				}

//				@Override
//				public Component setDefaultModel(IModel model) {
//					// TODO Auto-generated method stub
//					return null;
//				}

	        });
	}
}
