package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ServletContextListener.super.contextInitialized(sce);
	}
}
