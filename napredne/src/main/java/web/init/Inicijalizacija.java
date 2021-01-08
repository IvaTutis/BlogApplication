package web.init;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



/**
 * Class implements {@link ServletContextListener}.
 * @author Lucija Valentic
 *
 */
@WebListener
public class Inicijalizacija implements ServletContextListener {

	/**
	 * {@inheritDoc}
	 */
	public void contextInitialized(ServletContextEvent sce) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}