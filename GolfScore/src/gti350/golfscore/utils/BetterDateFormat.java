package gti350.golfscore.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * This class formats dates like this: June 21, 2014.
 * 
 * @author Simon RG
 */
public class BetterDateFormat {
	private int day;
	private int month;
	private int year;
	
	public BetterDateFormat(Date d) {
		// is it still breaking the law of demeter if it's a singleton?
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.month = cal.get(Calendar.MONTH);
		this.year = cal.get(Calendar.YEAR);
	}
	
	/**
	 * Returns a date like this: June 21, 2014.
	 * @return date like this: June 21, 2014.
	 */
	public String format() {
		return getMonthName() + " " + day + ", " + year;
	}
	
	private String getMonthName() {
		// Months are zero-based.
		switch (month) {
			case 0: return "January";
			case 1: return "February";
			case 2: return "March";
			case 3: return "April";
			case 4: return "May";
			case 5: return "June";
			case 6: return "July";
			case 7: return "August";
			case 8: return "September";
			case 9: return "October";
			case 10: return "November";
			case 11: return "December";
			default: return "";
		}
	}
	
}
