package database;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.bson.BasicBSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import model.beans.Autor;
import model.beans.Komentar;
import model.beans.Tekst;
import model.beans.Tema;
/**
 * Class that implements interface InformationProvider
 * @author Lucija
 *
 */
public class IPImplementation implements InfromationProvider{

	/**
	 * {@inheritDoc}
	 */
	public List<Tekst> getAllTextsByAuthorUsername(String username) throws DBException, UnknownHostException {
		
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("autor", username);
	    DBCursor cursor = collection.find(whereQuery);
	   
	    List<Tekst> tekstovi = new ArrayList<Tekst>();

	    while(cursor.hasNext()) {
	    	BasicBSONObject t = (BasicBSONObject) cursor.next();
	      
	        
	        List<Komentar> listKomentara = getAllCommentsByTextId((int)t.getInt("text_id"));
	        List<Tema> listTema = getAllThemesByTextId((int)t.getInt("text_id"));
	        List<String> listSlika = getAllPicturesByTextId((int)t.getInt("text_id"));
	        Tekst tmpT = Util.makeText((int)t.getInt("text_id"), (String)t.getString("naslov"), 
	        		(String)t.getString("text"), username, listKomentara, listTema, listSlika);
	        
	        tekstovi.add(tmpT);
	    }
	    Util.close();
	    return tekstovi;
	}

	/**
	 * {@inheritDoc}
	 */
	public Autor getAutorByUsername(String username) throws DBException, UnknownHostException {
		
		DBCollection collection = Util.getCollection("Test", "login");
		BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("korisnicko_ime", username);
	    DBCursor cursor  = collection.find(whereQuery);
	    
	    if(cursor == null) {
	    	Util.close();
	    	return null;
	    }else {
	    	
	    	BasicBSONObject q = null;
	    	try {
	    		q = (BasicBSONObject) cursor.next();
	    	}catch(NoSuchElementException ex) {
	    		Util.close();
		    	return null;
	    	}
		    
		    Autor autor = Util.makeAuthor((String)q.get("korisnicko_ime"), (String)q.get("sifra"));
		    Util.close();
		    return autor;
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Komentar> getAllCommentsByTextId(int id) throws DBException, UnknownHostException {
		
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("text_id", id);
		DBCursor cursor = collection.find(whereQuery);
		if(cursor == null || !cursor.hasNext()) {
			Util.close();
			return null;
		}else {
			BasicBSONObject q = (BasicBSONObject) cursor.next();
			//System.out.println(q);
		    List<BasicBSONObject> list = (List<BasicBSONObject>) q.get("komentari");
		    List<Komentar> komentari = new ArrayList<Komentar>();
		    for(BasicBSONObject o : list) {
		    	String text = (String) o.get("text");
		    	String username = (String) o.get("korisnicko_ime");
		    	Komentar tmp = new Komentar();
		    	tmp.setAutor(username);
		    	tmp.setText(text);
		    	komentari.add(tmp);
		    }
		    Util.close();
		    return komentari;
		}
		    
	}
	/**
	 * {@inheritDoc}
	 */
	public List<Autor> getAllAuthors() throws DBException, UnknownHostException {

		DBCollection collection = Util.getCollection("Test", "login");
		
		DBCursor cursor = collection.find();
		if(!cursor.hasNext()) {
			Util.close();
			return null;
		}
		List<Autor> autori = new ArrayList<Autor>();	
		while (cursor.hasNext()) {
			BasicBSONObject o = (BasicBSONObject) cursor.next();
		    String username = o.getString("korisnicko_ime");
		    String pass = o.getString("sifra");
			Autor autor = Util.makeAuthor(username, pass);
			autori.add(autor);
		}
		
		return autori;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void registerNewAutor(Autor autor) throws DBException, UnknownHostException {
		DBCollection collection = Util.getCollection("Test", "login");

		DBObject a = new BasicDBObject("korisnicko_ime", autor.getUsername()).append("sifra", autor.getSifra());

		collection.insert(a);		
		Util.close();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addNewText(Tekst text, Autor autor) throws DBException, UnknownHostException {
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		
		ArrayList<BasicBSONObject> list3 = new ArrayList<BasicBSONObject>();
		
		   
		DBObject tekst = new BasicDBObject("text_id", text.getId())
	               .append("text", text.getText()).append("naslov", text.getNaslov())
	               .append("autor", autor.getUsername())
	               .append("teme", text.getTeme()).append("komentari", list3).append("slike", text.getSlike());
		   
		   collection.insert(tekst);
		   Util.close();
		
	}
	/**
	 * {@inheritDoc}
	 */
	public void changeExistingText(Tekst text) throws DBException, UnknownHostException {
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append("text_id", text.getId());
		   	
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set",
				new BasicDBObject().append("text", text.getText()));
			
		collection.update(searchQuery, updateQuery);
		Util.close();
		
	}
	/**
	 * {@inheritDoc}
	 */
	public void addNewComment(Komentar comment, int id) throws DBException, UnknownHostException {
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		DBObject findQuery = new BasicDBObject("text_id", id);
		DBObject listItem = new BasicDBObject("komentari", new BasicDBObject("korisnicko_ime", comment.getAutor().getUsername()).append("text",comment.getText()));
		DBObject updateQuery2 = new BasicDBObject("$push", listItem);
		collection.update(findQuery,updateQuery2);
		Util.close();
	}
	/**
	 * {@inheritDoc}
	 */
	public Tekst getTekstById(int id) throws UnknownHostException {
		
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("text_id", id);
		DBCursor cursor = collection.find(whereQuery);
		
		if(cursor == null) {
			Util.close();
			return null;
		}else {
			BasicBSONObject q = (BasicBSONObject) cursor.next();
			
			String tekst = q.getString("text");
			
			String naslov = q.getString("naslov");
			
			Autor autor = null;
			if (q.getString("autor") != null) {
			autor = getAutorByUsername(q.getString("autor"));}
			List<Komentar> komentari = getAllCommentsByTextId(id);
			List<Tema> teme = getAllThemesByTextId(id);
			List<String> slike = getAllPicturesByTextId(id);
			Tekst tmp = Util.makeText(id, naslov, tekst, q.getString("autor"), komentari, teme, slike);
			Util.close();
			return tmp;
			
		}
	   
	}
	/**
	 * {@inheritDoc}
	 */
	public List<Tema> getAllThemesByTextId(int id) throws UnknownHostException {
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("text_id", id);
		DBCursor cursor = collection.find(whereQuery);
		
		if(cursor == null || !cursor.hasNext()) {
			Util.close();
			return null;
		}else {
			
			BasicBSONObject q = (BasicBSONObject) cursor.next();		
			List<String> list = (List<String>) q.get("teme");
		    List<Tema> teme = new ArrayList<Tema>();
		    if(list != null) {
			    for(String o : list) {
		
			    	Tema t = new Tema();
			    	t.setImeTeme(o);
			    	teme.add(t);
			    }
			}else {
		    	teme = null;
		    }
		    Util.close();
		    return teme;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public List<String> getAllPicturesByTextId(int id) throws UnknownHostException {
		DBCollection collection = Util.getCollection("Test", "tekstovi");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("text_id", id);
		DBCursor cursor = collection.find(whereQuery);
		
		if(cursor == null || !cursor.hasNext()) {
			Util.close();
			return null;
		}else {
			BasicBSONObject q = (BasicBSONObject) cursor.next();		
			List<String> list = (List<String>) q.get("slike");
		   
		    Util.close();
		    return list;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public List<Tekst> getAllTekst() throws DBException, UnknownHostException {
		List<Autor> autori = getAllAuthors();
		
		List<Tekst> lista = new ArrayList<Tekst>();
		
		for(Autor a : autori) {
			List<Tekst> tmp = getAllTextsByAuthorUsername(a.getUsername());
			lista.addAll(tmp);
		}
		return lista;
	}

}
