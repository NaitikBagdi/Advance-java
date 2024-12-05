package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * A utility class for managing database connections
 * This class provides methods to establish a connection to the database
 * It relies on JDBC and MySQL for database interaction
 *
 * @author Naitik Bagdi
 */
public class DataBaseConnection {

	private static String url = "jdbc:mysql://localhost:3306/employees_registration";
	private static String userName = "root";
	private static String password = "naitik123";
	private static String className = "com.mysql.cj.jdbc.Driver";

	/**
 	 * Get a connection to the database
	 * 
	 * @return A Connection object representing the database connection, or null if an error occurs
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(className);
			// Establish the database connection
			connection = DriverManager.getConnection(url, userName, password);
		} catch (Exception exception) {
			// Print stack trace in case of an error
			exception.printStackTrace();
		}
		return connection;
	}

}
