package database;
/**
 * Class that extends {@link RuntimeException}, and it is made to 
 * signal when there is something wrong with database
 * @author Lucija
 *
 */
public class DBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message
	 */
	public DBException(String message) {
		super(message);
	}
}
