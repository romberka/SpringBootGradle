package com.amwater.microservices.demo.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	public static String trimLeadingZeros(String str) { // NO_UCD (unused code)
		if (str == null) {
			return null;
		}
		char[] chars = str.toCharArray();
		int index = 0;
		for (; index < str.length(); index++) {
			if (chars[index] != '0') {
				break;
			}
		}
		return (index == 0) ? str : str.substring(index);
	}

	public static String getMaskedString(String inString, boolean suffixMask) {
		String returnString = inString.trim();

		if (suffixMask) {
			if (returnString.length() > 4) {
				StringBuilder sb = new StringBuilder(returnString);

				for (int i = 5; i < inString.trim().length(); i++) {
					sb.setCharAt(i, '*');
				}

				returnString = sb.toString();
			}
		} else {
			returnString = getMaskedString(inString);
		}

		return returnString;
	}

	public static String getMaskedString(String inString) {
		String returnString = inString.trim();

		if (returnString.length() > 4) {
			StringBuilder sb = new StringBuilder(returnString);

			for (int i = 0; i < inString.trim().length() - 4; i++) {
				sb.setCharAt(i, '*');
			}

			returnString = sb.toString();
		}

		return returnString;
	}

	public static String getMaskedString(String inString, int outputLength, int nbrCharToShow) {
		inString = StringUtils.trimToEmpty(inString);

		String returnString = inString;

		int inStringLen = inString.length();

		if (inStringLen < nbrCharToShow) {
			nbrCharToShow = inStringLen;
		}

		if (inStringLen > 0 && outputLength > nbrCharToShow) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < outputLength - nbrCharToShow; i++) {
				sb.insert(0, '*');
			}

			sb.append(inString.substring(inStringLen - nbrCharToShow));

			returnString = sb.toString();
		}

		return returnString;
	}

	public static boolean isEmailAddressValid(String emailAddress) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailAddress);

		return matcher.matches();
	}

	public static boolean isNumber(String input) {
		boolean returnValue = false;
		try {
			Long.parseLong(input);
			returnValue = true;
		}
		catch (NumberFormatException nfe) {
			// ignore
		}
		return returnValue;
	}

	public static boolean isEmployerIdNumberValid(String employerIdNumber) {
		String regexpPattern = "^\\d{2}-\\d{7}$";

		Pattern pattern = Pattern.compile(regexpPattern);
		Matcher matcher = pattern.matcher(employerIdNumber);

		return matcher.matches();
	}

	public static boolean isValid24HourTimeStampTime(String time) { // NO_UCD (unused code)
		boolean isValidTime = true;
		String pattern = "[012]{0,1}\\d{1}:[0-5]{0,1}\\d{1}((:[0-5]{1}\\d{1})?|(:[0-5]{1}\\d{1}.\\d{0,3})?)?";
		if (null == time || time.trim().length() < 1) {
			isValidTime = false;
		} else {
			isValidTime = time.matches(pattern);
		}

		return isValidTime;
	}

	public static String padLeadingZeros(String string, int stringLength) { // NO_UCD (unused code)
		String returnString = "";
		if (string != null && !string.trim().equals("")) {
			returnString = String.format("%0" + stringLength + "d", new Integer(string.trim()).intValue());
		}
		return returnString;
	}

	public static boolean containsCaseInsensitive(String s, List<String> l) {
		for (String string : l) {
			if (trimToEmpty(string).equalsIgnoreCase(trimToEmpty(s))) {
				return true;
			}
		}
		return false;
	}

	public static long calculateSeed(String s) {
		long seed = 0;

		for (char ch : s.toCharArray()) {
			int pos = ch;
			seed = seed + pos;
		}

		return seed;
	}
}
