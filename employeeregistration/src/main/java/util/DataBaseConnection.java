package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

	private static String url = "jdbc:mysql://localhost:3306/employees_registration";
	private static String userName = "root";
	private static String password = "naitik123";
	private static String className = "com.mysql.cj.jdbc.Driver";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(className);
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
