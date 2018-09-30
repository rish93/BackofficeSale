package com.ketli.app.wicket;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class LinkCounter  extends WebPage{
	private Label ajaxlabel;
	private int counter;
	private int ajaxCounter;
	public LinkCounter() {
		add(new Link("simplelink")
				{

					@Override
					public MarkupContainer setDefaultModel(IModel model) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void onClick() {
						// TODO Auto-generated method stub
					
						counter++;
					}
				}
				);
	add(new Label("label",new PropertyModel(this, "counter")));
	
	
	
	add (new AjaxLink("ajaxlink") {

		@Override
		public MarkupContainer setDefaultModel(IModel model) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void onClick(AjaxRequestTarget target) {
			// TODO Auto-generated method stub
			ajaxCounter++;
			if(target != null) {
				target.add(ajaxlabel);
			}
		}

	});
	ajaxlabel = new Label("ajaxlabel", new PropertyModel(this, "ajaxCounter"));
	ajaxlabel.setOutputMarkupId(true);
	add(ajaxlabel);
	
	
	}
	
	
}
