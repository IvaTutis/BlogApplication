package web.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBProvider;
import model.beans.Autor;



/**
 * Class extends class {@link HttpServlet}. This class
 * through its method, allows user to login in, see
 * other registered users, and maybe, if user is logged in, to
 * log out. This class with its method prepares all for that actions
 * 
 * @author Lucija 
 *
 */
@WebServlet("/servleti/main")
public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Autor> u = DBProvider.getIPProvider().getAllAuthors();
		
		if(u != null && !u.isEmpty()) {
			
			Map<String, String> users = new HashMap<String, String>();
			
			for(Autor user : u) {
				users.put(user.getUsername(), user.getUsername());
			}
			
			req.setAttribute("users", users);
		}
	
		req.getRequestDispatcher("/WEB-INF/pages/Main.jsp").forward(req, resp);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("currentUserId") != null) {
			doGet(req, resp);
			return;
		}
		
		String username = req.getParameter("user");
		
		req.setAttribute("usernameForm", username);
		
		String password = req.getParameter("pass");
		
		if(password == null || password.isBlank()) {
			req.setAttribute("loginError", "Unesena je kriva lozinka i/ili username!");
			doGet(req, resp);
			return;
		}
		
		if(username == null || username.isBlank()) {
			req.setAttribute("loginError", "Unesena je kriva lozinka i/ili username!");
			doGet(req, resp);
			return;
		}
		
		Autor autor = DBProvider.getIPProvider().getAutorByUsername(username);
		
		if(autor == null) {
			req.setAttribute("loginError", "Unesena je kriva lozinka i/ili username!");
			doGet(req, resp);
			return;
		}
		
		if(!autor.getSifra().equals(password)) {
			req.setAttribute("loginError", "Unesena je kriva lozinka i/ili username!");
			doGet(req, resp);
			return;
		}
		
		req.getSession().setAttribute("currentUserId", autor.getUsername());
		req.getSession().setAttribute("currentUserNick", autor.getUsername());
		
		doGet(req, resp);
	}
	
}
