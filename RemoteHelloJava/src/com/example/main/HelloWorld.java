package com.example.main;


import java.io.File;
import java.util.ArrayList;

import com.example.misc.StatisticsCalculator;
import com.example.misc.TextFormatter;
import com.example.models.WeatherStatistic;

/**
 * This is where you write documentation.
 * 
 * A simple class for introducing a Java application.
 * 
 * @author Amr Hisham
 * @version 1.0.0
 */
class HelloWorld {
	
	/**
	 * Constructor.
	 */
	public HelloWorld() {

	}
	
	/**
	 * Print a given message.
	 * @return No Value.
	 */
	private static void printMessage(String msg) {
	    System.out.println(msg);
    }

	/**
	 * Phase One:
	 * 1. Parse a given text file.
	 * 2. Extract desired data in a formatted form.
	 * 3. Insert to extracted data into Database.
	 * @return No Value.
	 */
	private static void phaseOne() {
		// Create Class Objects.
		WeatherStatistic wStat = new WeatherStatistic();
		TextFormatter txtFormat = new TextFormatter();
		
		ArrayList<WeatherStatistic> statsArrList = new ArrayList<WeatherStatistic>();
		ArrayList<String> strArrList = new ArrayList<String>();
		
		// String s1 = "723150 03812  20000101    44.7 24    40.3 24 1022.0 21 9999.9  0      6.0 24     3.2 24    15.0    20.0    64.0     30.9   0.00D 999.9 100000 ";
		
		// Get file contents from given text-file.
		File statsFile = new File("C:\\Users\\Amr Hisham\\Desktop\\SampleWeatherStats.txt");
		strArrList = txtFormat.getFileContents(statsFile);
		
		// Remove the header since it is irrelevant.
		strArrList.remove(0);

		// Extract each string line from the strings ArrayList.
		for(String str : strArrList) {
			// Extract from the given text values at position 3 and position 14.
			// In this case, we need Columns at Index = {3, 14}.
			statsArrList = txtFormat.extractText(str, 3, 14);
			
			// Get fields from Stats ArrayList.
			for(WeatherStatistic ws : statsArrList) {
				printMessage("Stat. Date = " + txtFormat.formatDate(ws.getStatDate()));
				printMessage("Wind Speed = " + ws.getWindSpeed());
				
				// Attempt to insert to statistics to database.
				if (wStat.insertWeatherStat( txtFormat.formatDate(ws.getStatDate()), ws.getWindSpeed())) {
					printMessage("Record inserted at ID = " + wStat.getMaxID());
				}
			}
		}
		
		printMessage("Number of Objects = " + String.valueOf(wStat.numberOfObjects()));
    }
	
	/**
	 * Phase Two:
	 * 1. Get the All Wind-Speed Values from the Dataset.
	 * 2. Calculate Mean.
	 * 3. Calculate Median.
	 * @return No Value.
	 */
	private static void phaseTwo() {
		
		// Create Class Objects.
		WeatherStatistic wStat = new WeatherStatistic();
		StatisticsCalculator statsCalc = new StatisticsCalculator();

		printMessage("Wind-Speed Mean = " + String.valueOf(statsCalc.calculateWindSpeedMean(wStat.getAllWindSpeed())));
		printMessage("Wind-Speed Median = " + String.valueOf(statsCalc.calculateWindSpeedMedian(wStat.getAllWindSpeed())));	
	}
	
	/**
	 * Application entry point
	 * 
	 * @param args
	 * Array of command-line arguments passed to this method
	 */
	public static void main(String[] args) {
	// --------------------- Application Logic goes here ---------------------

		// Retrieving the Data.
		phaseOne();
		
		// Statistical Analysis.
		phaseTwo();
		
		
	}	// End of Main.
}	// End of Class.