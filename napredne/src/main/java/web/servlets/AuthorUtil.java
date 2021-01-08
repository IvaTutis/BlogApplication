package web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.BasicBSONObject;

import database.DBProvider;
import model.beans.Autor;
import model.beans.Komentar;
import model.beans.Tekst;
import model.beans.Tema;


/**
 * Class that has methods that deal with blog entries, adding
 * new blog entries, editing blog entries, and adding new comments.
 * 
 * @author Lucija 
 *
 */
public class AuthorUtil {

	/**
	 * Internal map of errors
	 */
	public static Map<String, String> errors = new HashMap<String, String>();
	
	/**
	 * Method gathers all blog entries from some user (user id is given through
	 * parameter). It then shows to the user list of all blogs from some user. List of
	 * blogs are actually links, and user can click on them and see actually blog entries
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void handleListOfBlogEntries(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] pathInfo = req.getPathInfo().substring(1).split("/");
		
		Autor user = DBProvider.getIPProvider().getAutorByUsername(pathInfo[0]);
		
		
		List<Tekst> blogEntries = DBProvider.getIPProvider().getAllTextsByAuthorUsername(user.getUsername());
		
		
		if(blogEntries != null && !blogEntries.isEmpty()) {
			req.setAttribute("blogEntries", blogEntries);
		}
		
		if(req.getSession().getAttribute("currentUserId") != null && req.getSession().getAttribute("currentUserNick").equals(pathInfo[0])) {
			req.setAttribute("addNew", true);
		}
		
		req.getRequestDispatcher("/WEB-INF/pages/ListOfBlogs.jsp").forward(req, resp);
	}

	/**
	 * Method prepares blog that was determinated by blogID that was
	 * sent through parameter. If user is logged in and 
	 * blog entries belongs to the user, then special button is prepared, and
	 * user can choose to edit it. This method in the end shows actual
	 * blog entry to the user
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void handleBlogEntryByID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] pathInfo = req.getPathInfo().substring(1).split("/");
		String lastString = pathInfo[pathInfo.length - 1];
		
		int id = Integer.parseInt(lastString);
		
		Tekst tekst = DBProvider.getIPProvider().getTekstById(id);
		
		if(tekst == null) {
			req.setAttribute("message", "Pogreška! Zatražili ste stranicu koja je zabranjena!");
			req.getRequestDispatcher("/WEB-INF/pages/Message.jsp").forward(req, resp);
			return;
		}
		
		req.setAttribute("blogEntry", tekst);
		
		List<Komentar> comments = DBProvider.getIPProvider().getAllCommentsByTextId(id);
		
		
		if(comments != null && !comments.isEmpty()) {
			req.setAttribute("comments", comments);
		}
		
		if(req.getSession().getAttribute("currentUserId") != null && req.getSession().getAttribute("currentUserNick").equals(pathInfo[0])) { // ako je ulogirani korisnik, baš vlasnik tog bloga
			req.setAttribute("edit", true);
		}
		
		Autor user = DBProvider.getIPProvider().getAutorByUsername(pathInfo[0]);
		
		req.getRequestDispatcher("/WEB-INF/pages/Blog.jsp").forward(req, resp);
	}

	/**
	 * Method checks if everything was typed in correctly by the user.
	 * If there was some mistakes, user has to add blog entry again. 
	 * in the end, this method allows user to add new blog entry.
	 * This action is only possible is some user is logged in
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void addNewBlogEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String text = req.getParameter("textAll");
		errors.clear();
		
		if(title == null || title.isBlank()) {
			errors.put("titleError", "Naslov je obavezno polje");
		}
		
		if(text == null || text.isBlank()) {
			errors.put("textError", "Sadržaj je obavezno polje");
		}
		
		if(!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/pages/NewBlog.jsp").forward(req, resp);
			return;
		}
		
		Tekst tekst = new Tekst();
		
		String username = String.valueOf(req.getSession().getAttribute("currentUserId"));
		
		
		Autor user = DBProvider.getIPProvider().getAutorByUsername(username);
		
		List<Tekst> lista = DBProvider.getIPProvider().getAllTekst();
	
		tekst.setAutor(user.getUsername());
		tekst.setId(lista.size() + 1);
		
		tekst.setText(text);
		
		tekst.setNaslov(title);
		List<Komentar> l1 = new ArrayList<Komentar>();
		tekst.setKomentari(l1);
		List<String> l2 = new ArrayList<String>();
		tekst.setSlike(l2);
		List<Tema> l3 = new ArrayList<Tema>();
		tekst.setTeme(l3);
		
		DBProvider.getIPProvider().addNewText(tekst, user);
		
		req.setAttribute("message", "Blog uspješno dodan!");
		 
		req.getRequestDispatcher("/WEB-INF/pages/Message.jsp").forward(req, resp);
		
	}

	/**
	 * Method allows user to edit some blog entry, determinate by
	 * blogID sent through parameter. This action is only allowed
	 * if the user is logged in and if blog entry belongs
	 * to him. Otherwise, user will see message about error. This method just prepares
	 * jsp-file for editing
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void prepareforEditBlogEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String[] pathInfo = req.getPathInfo().substring(1).split("/");
		
		Autor user = DBProvider.getIPProvider().getAutorByUsername(pathInfo[0]);
		//BlogUser user = DAOProvider.getDAO().getBlogUserByNick(pathInfo[0]);
		
		if(user == null || req.getSession().getAttribute("currentUserId") == null || 
				req.getSession().getAttribute("currentUserId") != null && !user.getUsername().equals(req.getSession().getAttribute("currentUserNick"))) {
			req.setAttribute("message", "Pogreška! Zatražili ste stranicu koja je zabranjena!");
			req.getRequestDispatcher("/WEB-INF/pages/Message.jsp").forward(req, resp);
			return;
		}
		
		int blogID = Integer.parseInt(req.getParameter("blogID"));
		
		Tekst entry = DBProvider.getIPProvider().getTekstById(blogID);
		//BlogEntry entry = DAOProvider.getDAO().getBlogEntryById(blogID);
		
		if(entry == null || !entry.getAutor().equals(user.getUsername()) ) {
			req.setAttribute("message", "Pogreška! Zatražili ste stranicu koja je zabranjena!");
			req.getRequestDispatcher("/WEB-INF/pages/Message.jsp").forward(req, resp);
			return;
		}
		
		req.setAttribute("blogEntry", entry);
		
		req.getSession().setAttribute("forEdit", true);
		req.getRequestDispatcher("/WEB-INF/pages/EditBlog.jsp").forward(req, resp);
		
	}


	/**
	 * Method allows user to edit some blog entry, determinate by
	 * blogID sent through parameter. This action is only allowed
	 * if the user is logged in and if blog entry belongs
	 * to him. Otherwise, user will see message about error. This method
	 * is actual method that allows user to edit blog entry
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void editBlogEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String text = req.getParameter("textAll");
		
		if(title == null || title.isBlank()) {
			errors.put("titleError", "Naslov je obavezno polje");
		}
		
		if(text == null || text.isBlank()) {
			errors.put("textError", "Sadržaj je obavezno polje");
		}
		
		if(!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/pages/EditBlog.jsp").forward(req, resp);
			return;
		}
		
		int id = Integer.parseInt(req.getParameter("blogID"));
		
		Tekst entry = DBProvider.getIPProvider().getTekstById(id);
		
		
		entry.setText(text);
		entry.setNaslov(title);
		
		DBProvider.getIPProvider().changeExistingText(entry);
		
		req.setAttribute("message", "Blog je uspješno promjenjen");
		req.getSession().removeAttribute("forEdit");
		req.getRequestDispatcher("/WEB-INF/pages/Message.jsp").forward(req, resp);
	}
	
}
