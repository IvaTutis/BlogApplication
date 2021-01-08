package web.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBProvider;
import model.beans.Autor;
import model.beans.Komentar;
import model.beans.Tekst;


/**
 * Class extends {@link HttpServlet}. This class allows
 * users to add new comments to some blog entries. Actions
 * are allowed only if some user is logged in. User 
 * can add comments to its own blog entry if he
 * chooses to.
 * 
 * @author Lucija 
 *
 */
@WebServlet("/servleti/comment")
public class CommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Internal map of errors
	 */
	Map<String, String> errors = new HashMap<String, String>();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("blogID");
		req.setAttribute("blogID", id);
		req.getRequestDispatcher("/WEB-INF/pages/AddComment.jsp").forward(req, resp);;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String message = req.getParameter("message");
		String username = req.getParameter("userName");
		
		errors.clear();
		
		if(message == null || message.isBlank()) {
			errors.put("messageError", "Poruka je obavezno polje");
		}
		
		Komentar comment = new Komentar();
		
		Autor user = null;
		try{
			user = DBProvider.getIPProvider().getAutorByUsername(String.valueOf(req.getSession().getAttribute("currentUserNick")));
		}catch(NoSuchElementException ex) {
			
		}
		
		if(req.getSession().getAttribute("currentUserId") != null) {
			comment.setAutor(user.getUsername());
			
		}else if(username == null || username.isBlank()) {
			errors.put("usersEmailError", "Username je obavezno polje");
		}else {
			Autor a = new Autor();
			a.setUsername(username);
			comment.setAutor(a);
			
		}
		
		if(!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/pages/AddComment.jsp").forward(req, resp);;
			return;
		}
		
		comment.setText(message);
		
		int id = Integer.parseInt(req.getParameter("blogID"));
		
		Tekst tekst = DBProvider.getIPProvider().getTekstById(id);
		
		DBProvider.getIPProvider().addNewComment(comment, id);
	
		req.setAttribute("message", "Komentar uspješno dodan");
		req.getRequestDispatcher("/WEB-INF/pages/Message.jsp").forward(req, resp);
		
	}
}
