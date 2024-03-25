package com.abclabs.business.utilities;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateFormatter {

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm a, z");
	
	public static String formatDates(Date date) {
		String theDate = null;
		if (date != null) {
			theDate = formatter.format(date);
		}
		
		return theDate;
	}
}
