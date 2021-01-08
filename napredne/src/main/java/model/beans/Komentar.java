package model.beans;
/**
 * Class represents comment
 * @author Lucija
 *
 */
public class Komentar {
	/**
	 * Author of the comment
	 */
	private Autor autor;
	/**
	 * Text of the comment
	 */
	private String text;
	/**
	 * Returns author
	 * @return
	 */
	public Autor getAutor() {
		return autor;
	}
	/**
	 * Sets author
	 * @param autor
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	/**
	 * Returns text of the comment
	 * @return
	 */
	public String getText() {
		return text;
	}
	/**
	 * Sets text of the comment
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Sets username of an author
	 * @param username
	 */
	public void setAutor(String username) {
		this.autor = new Autor();
		this.autor.setUsername(username);
	}
	/**
	 * Returns username of an author
	 * @return
	 */
	public String getUsername() {
		return autor.getUsername();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return autor.getUsername() +": "+ text;
	}
}
