package com.gmed.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {
	public static enum Mode {
		ALPHA, ALPHANUMERIC, NUMERIC 
	}
	private static int randomInt;
	public static Date getCurrentDate() {
		Calendar calendar = new GregorianCalendar();
		return calendar.getTime();
	}

	/**
	 * Return the system date in GregorianCalendar Format
	 * @return Calendar
	 */
	private static Calendar getCurrentCalendar() {
		return new GregorianCalendar();
	}



	/**
	 * Returns the system time in milliseconds
	 * @return timeInMilliseconds
	 */
	public static long getTimeInMillisOFCurrentCalender(){
		long timeInMillis = getCurrentCalendar().getTimeInMillis();
		return timeInMillis;
	}

	/**
	 * Format curretn date to the "yyyy-MM-dd" format
	 * @return date in "yyyy-MM-dd" format
	 */
	public static String getFormattedCurrentDate() {

		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		String time = timeFormat.format(new Date());

		return time;
	}

	public static String getCurrentDayOfMonth(){
		String currentDate = getFormattedCurrentDate();
		System.out.println(currentDate);

		String dayOfMonth = currentDate.substring(8, 10);
		System.out.println(dayOfMonth);
		return dayOfMonth;


	}

	public static String getFormattedCurrentDateFull(){

		SimpleDateFormat timeFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		String time = timeFormat.format(new Date());

		return time;

	}

	public static String getFormattedCurrentDateTime (){

		SimpleDateFormat timeFormat = new SimpleDateFormat("h");
		timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));

		String time = timeFormat.format(new Date());

		return time;

	}

	public static String getCurrentDateFormatted(){

		/*SimpleDateFormat timeFormat = new SimpleDateFormat("mm/dd/yyyy");
		//SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy");
		timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));

		String time = timeFormat.format(new Date());
		System.out.println(time);
		return time;*/


		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);

	}
	public static String getCurrentDateInDateFormatted(String formatname){

		/*SimpleDateFormat timeFormat = new SimpleDateFormat("dd/mm/yyyy");
		//SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy");
		timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));

		String time = timeFormat.format(new Date());
		System.out.println(time);*/
		DateFormat dateFormat = new SimpleDateFormat(formatname);
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);

	}

	public static void main(String args[]) {

		//		getFormattedCurrentDateFull();
		getCurrentDateFormatted();
	}

	public static String calendarDate() throws Throwable{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		Calendar cal = Calendar.getInstance();
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -randomStringGenerator());
		return dateFormat.format(cal.getTime());
	}

	public static int randomStringGenerator() throws Throwable{
		randomInt=Integer.parseInt(generateRandomString(1,DateUtil.Mode.NUMERIC));	
		return randomInt;
	}

	public static String generateRandomString(int length, Mode mode) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String characters = "";
		switch(mode){
		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;
		case NUMERIC:
			characters = "1234567890";
			break;
		}
		int charactersLength = characters.length();
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	/**
	 * Description - Method which returns a string date value of 'MM/DD/YYYY' based on an
	 * input parameter of how many days from current date user desires.
	 *
	 * Author - Jyoti kalra
	 *
	 * @param numberOfDays
	 * @return
	 */
	public static String someDaysInFuture(int numberOfDays) {
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, numberOfDays); // Adding some number of days specified in parameter
		String output = sdf.format(c.getTime());
		return output;
	}
	/**
	 * This method is used to fetch future date
	 * @return
	 */
	public static String getFutureDate(){
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

		// Create a calendar object with today date. Calendar is in java.util pakage.
		Calendar calendar = Calendar.getInstance();

		// Move calendar to yesterday
		calendar.add(Calendar.DATE, +1);

		// Get current date of calendar which point to the tommorrow now
		Date futureDate = calendar.getTime();

		String futureDateValue= dateFormat.format(futureDate).toString();
		return futureDateValue;
	}
	/**
	 * This method is used to fetch future date
	 * @return
	 */
	public static String getFutureYear(){
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

		// Create a calendar object with today date. Calendar is in java.util pakage.
		Calendar calendar = Calendar.getInstance();

		// Move calendar to yesterday
		calendar.add(Calendar.YEAR, +1);

		Date futureYear = calendar.getTime();

		String futureYearValue= dateFormat.format(futureYear).toString();
		return futureYearValue;		
		
	}
	/**
	 * This Method is used for getting current year
	 * @return current year
	 */
	public static int getCurrentYear(){
		int year = Year.now().getValue();
		return year;
	}
	
	/**
	 * This method is used to fetch future date in MM/dd/YYYY format
	 * @return
	 * @throws ParseException 
	 */
	public static String getFormattedDate(String date, String format) throws ParseException {
		
		DateFormat formatter = new SimpleDateFormat("M/d/yyyy"); 
		Date date1 = (Date)formatter.parse(date);
		return new SimpleDateFormat(format).format(date1);
		
      }

}
