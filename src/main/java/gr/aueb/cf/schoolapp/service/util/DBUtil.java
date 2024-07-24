package gr.aueb.cf.schoolapp.service.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtil {
	
	private static BasicDataSource ds = new BasicDataSource();
	private static Connection connection;
	
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/shool6db?serverTimeZone=UTC");
		ds.setUsername("userdb6");
		ds.setPassword(System.getenv("PASS_DB6"));
		ds.setInitialSize(10);
		ds.setMaxTotal(50);
		ds.setMinIdle(10);//san to elaxisto  gurnaei piso otan teleionei o xristis 
	}
	
	private DBUtil(){
		
	}
	public static Connection getConnection() throws SQLException{
		connection = ds.getConnection();
		return connection; 
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) connection.close();
					
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

}
