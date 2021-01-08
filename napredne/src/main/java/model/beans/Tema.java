package model.beans;
/**
 * Class represents theme of the text
 * @author Lucija
 *
 */
public class Tema {
	/**
	 * Name of the theme
	 */
	private String imeTeme;
	/**
	 * Returns name of the theme
	 * @return
	 */
	public String getImeTeme() {
		return imeTeme;
	}
	/**
	 * Sets name of the theme
	 * @param imeTeme
	 */
	public void setImeTeme(String imeTeme) {
		this.imeTeme = imeTeme;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return imeTeme;
	}
}
