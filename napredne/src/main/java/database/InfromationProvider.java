package database;

import java.net.UnknownHostException;
import java.util.List;

import model.beans.Autor;
import model.beans.Komentar;
import model.beans.Tekst;
import model.beans.Tema;
/**
 * Interface that, once implemented can extract informations from database
 * @author Lucija
 *
 */
public interface InfromationProvider {
	/**
	 * Returns all texts in database
	 * @param username
	 * @return List<Tekst>
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public List<Tekst> getAllTextsByAuthorUsername(String username) throws DBException, UnknownHostException;
	/**
	 * Returns Author with given username, or null if there isn't author with that username
	 * @param username
	 * @return Autor
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public Autor getAutorByUsername(String username) throws DBException, UnknownHostException;
	/**
	 * Returns all the comments from some text with given id
	 * @param id
	 * @return List<Komentar>
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public List<Komentar> getAllCommentsByTextId(int id) throws DBException, UnknownHostException;
	/**
	 * Returns all authors in database, or null if there isn't any
	 * @return List<Author>
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public List<Autor> getAllAuthors() throws DBException, UnknownHostException;
	/**
	 * Registers new author in database with given informations
	 * @param autor
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public void registerNewAutor(Autor autor) throws DBException, UnknownHostException;
	/**
	 * Adds new text in database with given informations
	 * @param text
	 * @param autor
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public void addNewText(Tekst text, Autor autor) throws DBException, UnknownHostException;
	/**
	 * It changes an existing text with given informations
	 * @param text
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public void changeExistingText(Tekst text) throws DBException, UnknownHostException;
	/**
	 * Adds new comment to text with given id
	 * @param comment
	 * @param id
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public void addNewComment(Komentar comment, int id) throws DBException, UnknownHostException;
	/**
	 * Returns text that has given id, or null if there isn't one
	 * @param id
	 * @return Tekst
	 * @throws UnknownHostException
	 */
	public Tekst getTekstById(int id) throws UnknownHostException;
	/**
	 * Returns all the themes from text
	 * @param id
	 * @return List<Tema>
	 * @throws UnknownHostException
	 */
	public List<Tema> getAllThemesByTextId(int id) throws UnknownHostException;
	/**
	 * Returns all the pictures from text
	 * @param id
	 * @return List<String>
	 * @throws UnknownHostException
	 */
	public List<String> getAllPicturesByTextId(int id) throws UnknownHostException;
	/**
	 * Returns all the text in database
	 * @return List<Tekst>
	 * @throws DBException
	 * @throws UnknownHostException
	 */
	public List<Tekst> getAllTekst() throws DBException, UnknownHostException;
}
