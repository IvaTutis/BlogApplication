package web.servlets;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBException;
import database.DBProvider;
import model.beans.Autor;

/**
 * Class extends {@link HttpServlet}. This class
 * allows user, with its method, to register himself.
 * If he enters something invalid, registration won't
 * be successful, and he will have to enter it again. If
 * everything is valid, then message about successful registration
 * will be shown to user.
 * 
 * @author Lucija 
 *
 */
@WebServlet("/servleti/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * Internal map of errors
	 */
	private Map<String, String> errors = new HashMap<String, String>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Autor user = new Autor();
		String password = req.getParameter("password");
		String nick = req.getParameter("username");
		errors.clear();
		
		if(nick == null || nick.isBlank()) {
			errors.put("username", "Username je obavezno polje");
		}else if(doesNickExists(nick)) {
			errors.put("username", "Korisnik sa upisanim usernameom već postoji");
		}else {
			user.setUsername(nick);
		}
		
		
		if(password == null || password.isBlank()) {
			errors.put("password", "Lozinka je obavezno polje");
		}else {
			user.setSifra(password);
		}
		
		if(!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			doGet(req, resp);
			return;
		}
		
		DBProvider.getIPProvider().registerNewAutor(user);
		req.setAttribute("message", "Uspješna registracija!");
		req.getRequestDispatcher("/WEB-INF/pages/Message.jsp").forward(req, resp);
			
	}

	/**
	 * Checks if user with given nick exists. Returns <code>true</code>
	 * if it does, <code>false</code> otherwise
	 * @param nick
	 * @return boolean
	 * @throws UnknownHostException 
	 * @throws DBException 
	 */
	private boolean doesNickExists(String nick) throws DBException, UnknownHostException {
		
		List<Autor> autori = DBProvider.getIPProvider().getAllAuthors();
		for(Autor a : autori) {
			if(a.equals(nick)) {
				return true;
			}
		}
		return false;
	
		
	}
	
}
