package database;
/**
 * Class that gives us object to operate with database
 * @author Lucija
 *
 */
public class DBProvider {
	/**
	 * Internal dao
	 */
	private static InfromationProvider provider = new IPImplementation();
	
	/**
	 * Returns this.dao
	 * @return DAO
	 */
	public static InfromationProvider getIPProvider() {
		return provider;
	}
}
