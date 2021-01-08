package model.beans;
/**
 * Class that represents Author
 * @author Lucija
 *
 */
public class Autor {
	/**
	 * String username
	 */
	private String username;
	/**
	 * String password
	 */
	private String sifra;
	/**
	 * Return username
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Returns sifra
	 * @return
	 */
	public String getSifra() {
		return sifra;
	}
	/**
	 * Sets sifra
	 * @param sifra
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Username: " + username;
	}
}
