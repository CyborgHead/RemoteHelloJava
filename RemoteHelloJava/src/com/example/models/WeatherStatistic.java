package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.datalayer.DatabaseAccess;

/**
 * Manage Weather Statistics
 * @author Amr Hisham
 *
 */
public class WeatherStatistic {

	// --------------------- Instance Variables (Fields) ---------------------
	private int id = 0;
	private String statDate = null;
	private float windSpeed = 0.0f;
	
	// Variable stores the number of objects instantiated.
	private static int increment = 0;
	
	// Create a Database-Access Object.
	DatabaseAccess dba = new DatabaseAccess();
	
	// --------------------- Getters/Setters ---------------------
	
	public int getID() {
		return id;
	}

	private void setID(int id) {
		this.id = id;
	}

	public String getStatDate() {
		return statDate;
	}

	private void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	private void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	// --------------------- Constructors ---------------------
	
	public WeatherStatistic() {
		 increment();
	}
	
	public WeatherStatistic(String date, float speed) {		
		// Assign Fields.
		setStatDate(date);
		setWindSpeed(speed);
		
		increment();
	}
	
	// --------------------- Methods ---------------------
	
	/**
	 * Increments number of instantiated objects.
	 * @return No Value.
	 */
	private static void increment() {
		increment++;
	}
	
	// Get the number of instantiated objects for this class.
	public int numberOfObjects() {
		return increment;
	}
	
	// Get Maximum ID.
	public int getMaxID() {

		// Attempt to Connect.
		dba.Connect();
		
		// State your Query.
		String query = "SELECT MAX(id) AS id FROM weatherstats;";
		
		// Execute and get the result.
		ResultSet rs = dba.executeSelect(query);
		try {
			// Iterate over the result.
			while(rs.next()) {
				try {
					id = rs.getInt("id");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Do NOT forget to close the connection.
		dba.Disconnect();
		
		return id;		
	}
	
	// Get All Wind-Speed Records.
	public ArrayList<Float> getAllWindSpeed() {
		ArrayList<Float> speedArrList = new ArrayList<Float>();
		
		// Attempt to Connect.
		dba.Connect();
		
		// State your Query.
		String query = "SELECT wind_speed FROM weatherstats;";
		
		// Execute and get the result.
		ResultSet rs = dba.executeSelect(query);
		try {
			// Iterate over the result.
			while(rs.next()) {
				try {
					setWindSpeed(rs.getFloat("wind_speed"));
					speedArrList.add(getWindSpeed());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Do NOT forget to close the connection.
		dba.Disconnect();
		
		return speedArrList;		
	}
		
	// Insert a new record to the Weather Statistics.
	public Boolean insertWeatherStat(String statDate, float windSpeed) {
		// Attempt to Connect.
		dba.Connect();
		
		// State your Query.
		String query = "INSERT INTO weatherstats (stat_date, wind_speed) VALUES ('" + statDate + "', " + windSpeed + ");";
		
		// Execute and get the result.
		if (dba.executeNonQuery(query)) {
			// Do NOT forget to close the connection.
			dba.Disconnect();
			return true;
		} else {
			// Do NOT forget to close the connection.
			dba.Disconnect();
			return false;
		}
	}
	
	// Delete a record from the Weather Statistics.
	public Boolean deleteWeatherStat(int statID) {
		// Attempt to Connect.
		dba.Connect();
		
		// State your Query.
		String query = "DELETE FROM weatherstats WHERE id = " + statID + ";";
		
		// Execute and get the result.
		if (dba.executeNonQuery(query)) {
			// Do NOT forget to close the connection.
			dba.Disconnect();
			return true;
		} else {
			// Do NOT forget to close the connection.
			dba.Disconnect();
			return false;
		}
	}
	
	
}	// End of Class.