package com.example.misc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Calculate Statistics.
 * @author Amr Hisham
 *
 */
public class StatisticsCalculator {

	// Calculate Mean (Average) = sum of variables / count.
	public float calculateWindSpeedMean(ArrayList<Float> wsArrList) {
		float sumOfVars = 0.0f;
		float mean = 0.0f;
		
		// Add the sum of 
		for(float ws : wsArrList) {
			sumOfVars += ws;
		}
		
		// Mathematical Formula.
		mean = sumOfVars/wsArrList.size();
		
		return mean;
	}
	
	// Calculate Median = sort all variables then find the middle (half-way) number.
	public float calculateWindSpeedMedian(ArrayList<Float> wsArrList) {
		// Sort the ArrayList Ascending.
		Collections.sort(wsArrList);
		
		float median = 0.0f;
		
		/**
		 *  Finding the Middle Value:
	 	 *     If the number of items is even, then Median =  itemAtIndex (no. items / 2) + itemAtIndex ((no. items / 2)+1)
		 *     If the number of items is odd, then Median = itemAtIndex ((TruncateUp) no. items / 2)
		 */
		if(wsArrList.size() % 2 == 0) {	// Even.
			// System.out.println("Even");
			
			// Note that the ArrayList has Zero-Based index.
			float itemPart1 = wsArrList.get((wsArrList.size()/2) - 1);
			float itemPart2 = wsArrList.get(wsArrList.size()/2);

			median = (itemPart1 + itemPart2) / 2;
		} else {	// Odd.
			// System.out.println("Odd");
			median = wsArrList.get((int) Math.ceil(wsArrList.size()/2));
		}
		
		return median;
	}
	
	
}	// End of Class.