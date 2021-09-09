package com.app.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbConnection {
	
	private MySqlDbConnection() {
		
	}
	public static Connection connection;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/ecomapp";
		String userName="root";
		String password="Success@123";
		connection = DriverManager.getConnection(url, userName, password);
		return connection;
	}
}
