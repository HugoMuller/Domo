package servletContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.derby.tools.ij;
/**
 *
 * @author Hugo
 */

public class AppServletContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        System.out.println("ServletContextListener started");
        
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        System.out.println("ServletContextListener destroyed");
    }
}