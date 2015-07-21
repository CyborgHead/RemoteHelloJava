package com.example.datalayer;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Connect to a Database and Execute Basic Queries.
 * @author Amr Hisham
 *
 */
public class DatabaseAccess {
	
	// Database Credentials.
	private static final String dbURL = "jdbc:postgresql://localhost:5342/codeclinic";
	private static final String dbUserName = "postgres";
	private static final String dbUserPassword = "System_76";
	
	// Non-local Variables, and will be set and released via Connect and Disconnect methods, respectively.
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	// Connect to a Database.
	public String Connect() {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(dbURL, dbUserName, dbUserPassword);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			return "Connected";
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
			
			return e.getMessage();
		}
	}

	// Disconnect from Database.
	public String Disconnect() {
		try {
			stmt.close();
			conn.commit();
			conn.close();
			return "Disconnected";
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);

			return e.getMessage();
		}
	}
	
	// Execute a Query and Return Result in Resultset.
	public ResultSet executeSelect(String query) {
		try {
			rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Insert, Update, or Delete any record using given Query.
	public Boolean executeNonQuery(String query) {
		try {
			stmt.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}	// End of Class.