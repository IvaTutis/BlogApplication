package model.beans;

import java.util.List;
/**
 * Represents one text
 * @author Lucija
 *
 */
public class Tekst {
	/**
	 * Id of text
	 */
	private int id;
	/**
	 * Text
	 */
	private String text;
	/**
	 * Title
	 */
	private String naslov;
	/**
	 * Author
	 */
	private String autor;
	/**
	 * List of themes
	 */
	private List<Tema> teme;
	/**
	 * List of comments
	 */
	private List<Komentar> komentari;
	/**
	 * List of names of pictures
	 */
	private List<String> slike;
	/**
	 * Returns id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Returns text
	 * @return
	 */
	public String getText() {
		return text;
	}
	/**
	 * Sets text
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Returns title
	 * @return
	 */
	public String getNaslov() {
		return naslov;
	}
	/**
	 * Set title
	 * @param naslov
	 */
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	/**
	 * Returns author
	 * @return
	 */
	public String getAutor() {
		return autor;
	}
	/**
	 * Sets author
	 * @param autor
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	/**
	 * Returns list of themes
	 * @return
	 */
	public List<Tema> getTeme() {
		return teme;
	}
	/**
	 * Sets list of themes
	 * @param teme
	 */
	public void setTeme(List<Tema> teme) {
		this.teme = teme;
	}
	/**
	 * Returns list of comments
	 * @return
	 */
	public List<Komentar> getKomentari() {
		return komentari;
	}
	/**
	 * Sets list of comments
	 * @param komentari
	 */
	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}
	/**
	 * Returns names of pictures
	 * @return
	 */
	public List<String> getSlike() {
		return slike;
	}
	/**
	 * Set names of pictures
	 * @param slike
	 */
	public void setSlike(List<String> slike) {
		this.slike = slike;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Naslov: " + naslov;
	}
	
}
