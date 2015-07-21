package com.example.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.models.WeatherStatistic;

/**
 * Read and Format Text from Files and Strings.
 * @author Amr Hisham
 *
 */
public class TextFormatter {

	// Convert given string to a date format yyyy-MM-dd.
	// String must consist of 8 characters.
	public String formatDate(String dateStr) {
		
		StringBuilder formattedDate = new StringBuilder();
		
		formattedDate.append(dateStr.substring(0, 4));
		formattedDate.append("-");
		formattedDate.append(dateStr.substring(4, 6));
		formattedDate.append("-");
		formattedDate.append(dateStr.substring(6, 8));
	    
	    return formattedDate.toString();
    }
	
	// Use File-Reader to get Text Content.
	public ArrayList<String> getFileContents(File txtFile) {
		// Variable to store text.
		StringBuilder txtContent = new StringBuilder();
		
		// ArrayList to store text one line per item.
		ArrayList<String> strArrList = new ArrayList<String>();
		
		// Temp variable to store one line at a time.
		String txtLine = null;

		try {
			BufferedReader input = new BufferedReader(new FileReader(txtFile));
			try {
					while((txtLine = input.readLine()) != null) {
						// Replace text with newly-read line.
						txtContent.replace(0, txtContent.length(), txtLine);
						//txtContent.append(System.getProperty("line.separator"));
						
						strArrList.add(txtContent.toString());
					}
				} finally {
						input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return strArrList;
	}
	
	// Extracts separate values from a single line String.
	public void extractText(String line) {
		StringBuilder encounteredStr = new StringBuilder();
		StringBuffer sbuff = new StringBuffer();
		
		// Add the original string to buffer.
		sbuff.append(line); 
		
		// Variable to determine the current column index.
		int columnIndex = 0;
		
		// Replace until Buffer is exhausted.
		while(sbuff.length() > 0) {	
			try {
				columnIndex++;
				// Get the first encountered string from buffer and append it to the builder.
				encounteredStr.replace(0, encounteredStr.length(), sbuff.substring(0, sbuff.indexOf(" ")));
				
				// Remove the encountered string from Buffer.
				sbuff.delete(0, encounteredStr.length());

				// Remove extra spaces at the beginning.
				while(Character.isWhitespace(sbuff.charAt(0))) {
					sbuff.deleteCharAt(0);
				}
			
				// Print Counter.
				System.out.println("Column Index = " + String.valueOf(columnIndex));
			} catch (StringIndexOutOfBoundsException e) {
				//e.printStackTrace();
				break;
			}
			
			// Print Encountered String.
			System.out.println("Encountered String: " + encounteredStr.toString());
			
			// Print Remaining String.
			System.out.println("Remaining String: " + sbuff.toString());
		}	// End of Loop.
	}

	// This overload extracts separate values from a single line-string and creates objects indicated by column index.
	public ArrayList<WeatherStatistic> extractText(String line, int indx1, int indx2) {
		StringBuilder encounteredStr = new StringBuilder();
		StringBuffer sbuff = new StringBuffer();
		
		// Add the original string to buffer.
		sbuff.append(line); 
		
		// Variable to determine the current column index.
		int columnIndex = 0;
		
		// Number of values should be equal to number of indices.
		String dateVal = null;
		float speedVal = 0.0f;
		
		// ArrayList to hold the created objects.
		ArrayList<WeatherStatistic> weatherArrList = new ArrayList<WeatherStatistic>();
		
		// Replace until Buffer is exhausted.
		while(sbuff.length() > 0) {	
			try {
				columnIndex++;
				// Get the first encountered string from buffer and append it to the builder.
				encounteredStr.replace(0, encounteredStr.length(), sbuff.substring(0, sbuff.indexOf(" ")));
				
				// Remove the encountered string from Buffer.
				sbuff.delete(0, encounteredStr.length());

				// Remove extra spaces at the beginning.
				while(Character.isWhitespace(sbuff.charAt(0))) {
					sbuff.deleteCharAt(0);
				}
			
				// Set string values as soon as indices match.
				if(columnIndex == indx1) {
					System.out.println("Current Index Matching at " + columnIndex);
					dateVal = encounteredStr.toString();
				}
				if(columnIndex == indx2) {
					System.out.println("Current Index Matching at " + columnIndex);
					speedVal = Float.valueOf(encounteredStr.toString());
				}
			} catch (StringIndexOutOfBoundsException e) {
				//e.printStackTrace();
				break;
			}
			
			// Print Encountered String.
			// System.out.println("Encountered String: " + encounteredStr.toString());
			
			// Print Remaining String.
			// System.out.println("Remaining String: " + sbuff.toString());
		}	// End of Loop.
		
		// Create a new object and add it to the ArrayList..
		weatherArrList.add(new WeatherStatistic(dateVal, speedVal));
		
		return weatherArrList;
	}


}	// End of Class.