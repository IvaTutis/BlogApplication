package web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBProvider;
import model.beans.Autor;

/**
 * Class extends {@link HttpServlet}. Redirects user to
 * "/servleti/main"
 * 
 * @author Lucija 
 *
 */
@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Autor autor = DBProvider.getIPProvider().getAutorByUsername("Tea");
		req.setAttribute("autor", autor);
		req.getRequestDispatcher("/WEB-INF/pages/Demo.jsp").forward(req, resp);
	}

}
