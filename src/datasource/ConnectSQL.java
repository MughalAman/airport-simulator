package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	
	private static Connection conn = null;
	
	public static Connection getInstance() {
		
	
	if (conn==null) {
		// tee yhteys
		try {
			conn = DriverManager.getConnection("jdbc:mysql://10.114.34.11/airportsim_palvelupisteet?user=username&password=password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Yhteys luotu");
		return conn;
	}
	else
		return conn;
	}
}