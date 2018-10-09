package com.ketli.app.wicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class TabbedPanelPage extends WebPage {

	public TabbedPanelPage()
	  {
	  List tabs = new ArrayList();
	  tabs.add(new AbstractTab(new Model("first tab"))
	  {
	  public Panel getPanel(String panelId)
	  {
	  return new TabPanel1(panelId);
	  }
	  });

	  tabs.add(new AbstractTab(new Model("second tab"))
	  {
	  public Panel getPanel(String panelId)
	  {
	  return new TabPanel2(panelId);
	  }
	  });

	  tabs.add(new AbstractTab(new Model("third tab"))
	  {
	  public Panel getPanel(String panelId)
	  {
	  return new TabPanel3(panelId);
	  }
	  });

	  add(new AjaxTabbedPanel("tabs", tabs));
	  }

}
