package database;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import model.beans.Autor;
import model.beans.Komentar;
import model.beans.Tekst;
import model.beans.Tema;
/**
 * Utility class that has some useful functions
 * @author Lucija
 *
 */
public class Util {

	/**
	 * private mongo client
	 */
	private static MongoClient mongoClient;
	/**
	 * Function recives informations about text, and then return object  Tekst
	 * @param textID
	 * @param naslov
	 * @param text
	 * @param autor
	 * @param listKomentara
	 * @param listTema
	 * @param listSlika
	 * @return
	 */
	public static Tekst makeText(int textID, String naslov, String text, String autor, List<Komentar> listKomentara,
			List<Tema> listTema, List<String> listSlika) {
		
		Tekst tekst = new Tekst();
		tekst.setAutor(autor);
		tekst.setId(textID);
		tekst.setKomentari(listKomentara);
		tekst.setNaslov(naslov);
		tekst.setSlike(listSlika);
		tekst.setText(text);
		tekst.setTeme(listTema);
		return tekst;
	}
	/**
	 * Function receives informations about Author, and then it return new author with that information
	 * @param username
	 * @param pass
	 * @return
	 */
	public static Autor makeAuthor(String username, String pass) {
		boolean c = true;
		boolean t = true;
		
		if(!c) {
			c = false;
		}
		if(!t) {
			t = false;
		}
		Autor autor = new Autor();
	
		autor.setUsername(username);
		autor.setSifra(pass);
		return autor;
		
	}
	/**
	 * Function recieves information about comment, and then it return new comment with that informations
	 * @param autor
	 * @param text
	 * @return
	 */
	public static Komentar makeComment(Autor autor, String text) {
		Komentar k = new Komentar();
		k.setText(text);
		k.setAutor(autor);
		return k;
	}
	/**
	 * Functions make new Tema with given information
	 * @param name
	 * @return
	 */
	public static Tema makeTheme(String name) {
		Tema t = new Tema();
		t.setImeTeme(name);
		return t;
	}
	/**
	 * Function returns given collection from given database
	 * @param database
	 * @param collection
	 * @return
	 * @throws UnknownHostException
	 */
	public static DBCollection getCollection(String database, String collection) throws UnknownHostException {
		mongoClient = new MongoClient();
		DB db = mongoClient.getDB(database);
		return db.getCollection(collection);
	}
	/**
	 * Function closes all links to database
	 */
	public static void close() {
		mongoClient.close();
	}
	

}
