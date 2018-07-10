package com.amwater.microservices.demo.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ConverterUtils {
	private static DatatypeFactory datatypeFactory = null;

	static {
		init();
	}

	private static void init() {
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		}
		catch (Exception e) {}
	}

	// Redirect Status
	public static boolean redirectBololean(String status) {
		if (status == null)
			return false;

		return status.equalsIgnoreCase("001") ? true : false;
	}

	// Set String "Y"/"N" from boolean;
	public static String toYNString(Boolean b) {
		if (b == null)
			return null;
		return (b) ? "Y" : "N";
	}

	// Set String "Y"/"N" from boolean (Swapped);
	public static String toYNStringSwap(Boolean b) {
		if (b == null)
			return null;
		return (b) ? "N" : "Y";
	}

	// Set String "YES"/"NO" from String "Y"/"N";
	public static String toYNString(String str) {
		if (str == null)
			return "";
		return (str.equalsIgnoreCase("Y")) ? "Y" : "N";
	}

	// Set String "Y"/"N" from String "YES"/"NO";
	public static String toYesNoString(String str) {
		if (str == null)
			return "N";
		return (str.equalsIgnoreCase("YES")) ? "Y" : "N";
	}

	// Set String "Y"/"N" from x string code;
	public static String toYNStringX(String xstr) {
		if (xstr == null)
			return "N";
		return (xstr.equals("X")) ? "Y" : "N";
	}

	public static boolean toBoolean(String ynStr) {
		if (ynStr == null)
			return false;
		return (StringUtils.equalsIgnoreCase(ynStr, "Y")) ? true : false;
	}

	public static boolean toBooleanSwap(String ynStr) {
		if (ynStr == null)
			return false;
		return (StringUtils.equalsIgnoreCase(ynStr, "N")) ? true : false;
	}

	public static boolean toBooleanX(String xStr) {
		if (xStr == null)
			return false;
		return (StringUtils.equalsIgnoreCase(xStr, "X")) ? true : false;
	}

	public static boolean toBooleanXSwap(String xStr) { // NO_UCD (unused code)
		if (xStr == null)
			return false;
		return (StringUtils.equalsIgnoreCase(xStr, "X")) ? false : true;
	}

	public static boolean toBooleanXSwapReverse(String xStr) { // NO_UCD (unused code)
		if (xStr == null)
			return true;
		return (StringUtils.equalsIgnoreCase(xStr, "X")) ? false : true;
	}

	public static XMLGregorianCalendar toXMLGregorianCalendar(Calendar cal) {
		GregorianCalendar gCal = null;
		XMLGregorianCalendar xmlGcal = null;

		if (cal != null && cal instanceof GregorianCalendar) {
			gCal = (GregorianCalendar) cal;
			xmlGcal = datatypeFactory.newXMLGregorianCalendar(gCal);
		}
		return xmlGcal;
	}

	public static XMLGregorianCalendar toXMLGregorianCalendarDate(Calendar cal) {
		GregorianCalendar gCal = null;
		XMLGregorianCalendar xmlGcal = null;

		if (cal != null && cal instanceof GregorianCalendar) {
			gCal = (GregorianCalendar) cal;
			xmlGcal = datatypeFactory.newXMLGregorianCalendar(gCal);
			xmlGcal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			xmlGcal.setTime(DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);

		}
		return xmlGcal;
	}

	public static XMLGregorianCalendar toXMLGregorianCalendarTime(Calendar cal) {
		GregorianCalendar gCal = null;
		XMLGregorianCalendar xmlGcal = null;

		if (cal != null && cal instanceof GregorianCalendar) {
			gCal = (GregorianCalendar) cal;
			xmlGcal = datatypeFactory.newXMLGregorianCalendar(gCal);
			xmlGcal.setDay(DatatypeConstants.FIELD_UNDEFINED);
			xmlGcal.setMonth(DatatypeConstants.FIELD_UNDEFINED);
			xmlGcal.setYear(DatatypeConstants.FIELD_UNDEFINED);
			xmlGcal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

		}
		return xmlGcal;
	}

	public static Calendar toCalendar(XMLGregorianCalendar xmlCalDate, XMLGregorianCalendar xmlCalTime) {
		Calendar cal = null;

		if (xmlCalDate != null) {
			cal = new GregorianCalendar();

			cal.set(Calendar.YEAR, xmlCalDate.getYear());
			cal.set(Calendar.MONTH, xmlCalDate.getMonth() - 1);
			cal.set(Calendar.DAY_OF_MONTH, xmlCalDate.getDay());
			if (xmlCalTime != null) {
				cal.set(Calendar.HOUR_OF_DAY, xmlCalTime.getHour());
				cal.set(Calendar.MINUTE, xmlCalTime.getMinute());
				cal.set(Calendar.SECOND, xmlCalTime.getSecond());
			} else {
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);

			}
		}
		return cal;
	}

	// ************************** Calendar Conversions ***************************
	// XML TimeDate to Calendar for time properties,etc
	public static Calendar toCalendar(XMLGregorianCalendar xmlCal) {
		if (xmlCal != null)
			return xmlCal.toGregorianCalendar();
		else
			return null;
	}

	public static String toStringDate(Calendar cal) {
		if (cal == null)
			return null;
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
		return dateformatter.format(cal.getTime());
	}

	public static String toStringPrettyDate(Calendar cal) { // NO_UCD (unused code)
		if (cal == null)
			return null;
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
		return dateformatter.format(cal.getTime());
	}

	public static String toStringTime(Calendar cal) {
		if (cal == null)
			return null;
		SimpleDateFormat dateformatter = new SimpleDateFormat("HH:mm:ss");
		return dateformatter.format(cal.getTime());
	}

	public static String toStringPrettyTime(Calendar cal) { // NO_UCD (unused code)
		if (cal == null)
			return null;
		SimpleDateFormat dateformatter = new SimpleDateFormat("HH:mm:ss");
		return dateformatter.format(cal.getTime());
	}

	public static String toPrettyString(Calendar cal) {
		if (cal == null)
			return null;
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformatter.format(cal.getTime());
	}

	public static Calendar toCalendarPrettyDate(String date) throws ParseException { // NO_UCD (unused code)
		if (date == null)
			return null;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(sdf.parse(date));

		return cal;

	}

	public static Calendar toCalendarDate(String date) throws ParseException { // NO_UCD (unused code)
		if (date == null)
			return null;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		cal.setTime(sdf.parse(date));

		return cal;

	}

	public static Calendar toCalendarDateTime(String date, String time) throws ParseException { // NO_UCD (unused code)
		if (date == null || time == null)
			return null;

		Calendar cal = Calendar.getInstance();
		String dateTime = date + time;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		cal.setTime(sdf.parse(dateTime));

		return cal;

	}

	// ************************** Numeric Conversions ****************************

	public static String toString(Long longval) {
		return (longval == null) ? null : longval.toString();
	}

	public static Long toLong(String val) {
		if (val == null)
			return null;

		val = StringUtils.deleteWhitespace(val);

		return Long.valueOf(val);
	}

	public static long toLongValue(String val) {
		if (val == null)
			return 0;

		val = StringUtils.deleteWhitespace(val);

		return Long.parseLong(val);
	}

	public static String toStringPhone(String val) {
		if (val == null)
			return "";

		val = StringUtils.deleteWhitespace(val);

		if (val.length() != 10)
			return "";

		return val;
	}

	public static BigDecimal toBigDecimal(String val) {
		if (val == null || val.equals(""))
			return new BigDecimal(0);
		else {
			val = StringUtils.trim(val);
			if (val.endsWith("-"))
				val = "-" + val.substring(0, val.length() - 1);
			return val == null ? null : new BigDecimal(val.trim());
		}
	}

	public static String toString(BigDecimal number) {
		String strDecimal = null;
		if (number != null)
			strDecimal = number.toPlainString();
		return strDecimal;
	}

	public static int toInt(Object object) {
		if (object == null) {
			return 0;
		}

		int number = 0;
		try {
			number = Integer.parseInt(object.toString());
		}
		catch (NumberFormatException e) {
			number = 0;
		}
		
		return number;
	}
}