package com.ketli.app.wicket;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.jquery.ui.interaction.draggable.Draggable;
import com.googlecode.wicket.jquery.ui.interaction.selectable.Selectable;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;

import com.googlecode.wicket.jquery.ui.interaction.droppable.Droppable;

import com.googlecode.wicket.kendo.ui.console.Console;
import com.ketli.app.DAO.LoginDao;
import com.ketli.app.util.HibernateUtil;


public class DraggableSelectablePage extends WebPage{
	WebMarkupContainer list1 ;
	WebMarkupContainer list2 ;
	public DraggableSelectablePage() {
		
		Form<?> form = new Form<Void>("draggableForm") {

			@Override
			protected void onSubmit() {

			System.out.println(list2);
				
			}

		};
	
		
		add(form);
		
		
//		form.add(username);
//		form.add(password);

		list1= new WebMarkupContainer("list1");
		list1.add(new AttributeAppender("note",new Model<String>("Alpha")));
		form.add(list1);
		
		list2 = new WebMarkupContainer("list2");
		list2.add(new AttributeAppender("note",new Model<String>("Alpha")));
		form.add(list2);
		
		
		WebMarkupContainer con1 = new WebMarkupContainer("item1");
		con1.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con1);
		
		WebMarkupContainer con2 = new WebMarkupContainer("item2");
		con2.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con2);
		
		WebMarkupContainer con3 = new WebMarkupContainer("item3");
		con3.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con3);
		
		WebMarkupContainer con4 = new WebMarkupContainer("item4");
		con4.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con4);
		
		WebMarkupContainer con5 = new WebMarkupContainer("item5");
		con5.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con5);
		
		WebMarkupContainer con6 = new WebMarkupContainer("item6");
		con6.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con6);
		
		WebMarkupContainer con7 = new WebMarkupContainer("item7");
		con7.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con7);
		
		WebMarkupContainer con8 = new WebMarkupContainer("item8");
		con8.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con8);
		
		WebMarkupContainer con9 = new WebMarkupContainer("item9");
		con9.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con9);
		
		WebMarkupContainer con10 = new WebMarkupContainer("item10");
		con10.add(new AttributeAppender("note",new Model<String>("Alpha")));
		list1.add(con10);
		
		
	}
	
	
}


//public class DraggableSelectablePage extends AbstractSelectablePage
//{
//	private static final long serialVersionUID = 1L;
//	private final FeedbackPanel feedback;
//	private final Selectable<String> selectable;
//
//	public DraggableSelectablePage()
//	{
//		List<String> list = Arrays.asList("item #1", "item #2", "item #3", "item #4", "item #5", "item #6");
//
//		// FeedbackPanel //
//		this.feedback = new JQueryFeedbackPanel("feedback");
//		this.add(this.feedback.setOutputMarkupId(true));
//
//		// Selectable //
//		this.selectable = new Selectable<String>("selectable", list) {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onSelect(AjaxRequestTarget target)
//			{
//				this.info("items: " + this.getDefaultModelObjectAsString());
//				target.add(feedback);
//			}
//		};
//
//		this.add(this.selectable);
//
//		// Selectable ListView //
//		this.selectable.add(new ListView<String>("items", list) {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			protected void populateItem(ListItem<String> item)
//			{
//				// Draggable //
//				Draggable<?> draggable = selectable.createDraggable("drag");
//				item.add(draggable);
//
//				// Label //
//				Label label = new Label("item", item.getModelObject());
//				label.add(AttributeModifier.append("style", "position: relative; top: 2px; vertical-align: top;"));
//				item.add(label);
//			}
//
//		});
//
//		// Droppable //
//		Droppable<?> droppable = this.newDroppable("droppable");
//		this.add(droppable);
//
//		// Droppable ListView //
//		droppable.add(new ListView<String>("items", this.selectable.getModel()) {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			protected void populateItem(ListItem<String> item)
//			{
//				item.add(new Label("item", item.getModelObject()));
//			}
//		});
//	}
//
//	/**
//	 * Gets a new Droppable.
//	 * By default 'over' and 'exit' ('out') events are disabled to minimize client/server round-trips.
//	 */
//	private Droppable<?> newDroppable(String id)
//	{
//		return new Droppable<Void>(id) {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onDrop(AjaxRequestTarget target, Component component)
//			{
//				info(String.format("Dropped %s", selectable.getModelObject()));
//
//				target.add(feedback);
//				target.add(this); //refresh the listview
//			}
//		};
//	}
//}