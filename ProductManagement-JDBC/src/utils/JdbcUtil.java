package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver"); // load driver
		Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/zoRRiifDkZ", "zoRRiifDkZ", "i8DnY87Yvf"); // create connection
		return con;
	}

}