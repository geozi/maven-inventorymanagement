package dao.util;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * The {@link DBUtil} class is responsible for establishing 
 * connections to the database.
 */
public class DBUtil {
	private static BasicDataSource ds = new BasicDataSource();
	private static Connection connection;
	
	private DBUtil() {
		
	}
	
	static {
		ds.setUrl("jdbc:mysql://localhost:randomPort/inventoryDB?serverTimezone=UTC");
		ds.setUsername("randomUser");
		ds.setPassword("randomPassword");
		ds.setInitialSize(8);
		ds.setMaxTotal(32);
		ds.setMinIdle(8);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);
	}
	
	public static Connection getConnection() throws SQLException {
		connection = ds.getConnection();
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
