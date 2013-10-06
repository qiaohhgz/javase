package com.jim.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class DateUtils {
	public static SimpleDateFormat dateFormatYYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	public static SimpleDateFormat dateFormatYYYY = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat dateFormatM = new SimpleDateFormat("M");
	public static SimpleDateFormat dateFormatMM = new SimpleDateFormat("MM");
	public static SimpleDateFormat dateFormatDD = new SimpleDateFormat("dd");
	public static SimpleDateFormat dateFormatYYYYM = new SimpleDateFormat("yyyyM");
	public static SimpleDateFormat dateFormatMMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat dateFormatMMDDYYYYHHMM = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	public static SimpleDateFormat dateFormatMMDDYYYYHHMMSS = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public static Date getNextDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}

	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, date, hourOfDay, minute, second);
		return c.getTime();
	}

	/**
	 * get date from string
	 * 
	 * @param format
	 * @param strDate
	 * @return
	 */
	public static Date getDateFromStringByFormat(String format, String strDate) {
		Date date = null;

		if ((format == null) || (format.trim().equals(""))) {
			format = "yyyy/MM/dd";
		}

		try {
			date = new SimpleDateFormat(format).parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formatSimpleDate(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			return sdf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return - if date1 > date 2 return value>0 if date1 = date 2 return
	 *         value=0 if date1 < date 2 return value<0
	 */
	public static int compareDate(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		int compare = calendar1.compareTo(calendar2);
		return compare;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthOfDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		return (calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int compareDateMonth(Date date1, Date date2) {
		int year1 = new Integer(String.format("%tY", date1));
		int year2 = new Integer(String.format("%tY", date2));
		int month1 = new Integer(String.format("%tm", date1));
		int month2 = new Integer(String.format("%tm", date2));
		if (year1 == year2) {
			if (month1 == month2)
				return 0;
			else if (month1 > month2)
				return 1;
			else
				return -1;
		} else if (year1 > year2)
			return 1;
		else
			return -1;
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return - if date1 > date 2 return 1 if date1 = date 2 return 0 if date1
	 *         < date 2 return -1
	 */
	public static int compareDateYear(Date date1, Date date2) {
		int year1 = new Integer(String.format("%tY", date1));
		int year2 = new Integer(String.format("%tY", date2));
		if (year1 == year2) {
			return 0;
		} else if (year1 > year2)
			return 1;
		else
			return -1;
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return - if date1 > date 2 return 1 if date1 = date 2 return 0 if date1
	 *         < date 2 return -1
	 */
	public static int compareDateDay(Date date1, Date date2) {
		int compare = compareDateMonth(date1, date2);
		if (compare != 0) {
			return compare;
		} else {
			int day1 = new Integer(String.format("%td", date1));
			int day2 = new Integer(String.format("%td", date2));

			if (day1 > day2) {
				return 1;
			} else if (day1 == day2) {
				return 0;
			} else {
				return -1;
			}

		}
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, (-1));
		return calendar.getTime();
	}

	public static Date getPreviousDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long seconds = date.getTime() - 24 * 3600 * 1000;
		calendar.setTimeInMillis(seconds);
		return calendar.getTime();
	}

	public static Date getNextMonthByCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	public static Date getFirstDayOfDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYearOfDate(date), getMonthOfDate(date) - 1, 1);
		return calendar.getTime();
	}

	public static Date getEndDayOfDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYearOfDate(date), getMonthOfDate(date) - 1, getMaxDaysOfMonth(date));
		return calendar.getTime();
	}

	public static Date getLastWeeklyOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		long seconds = date.getTime() - 7 * 24 * 3600 * 1000;
		calendar.setTimeInMillis(seconds);
		return calendar.getTime();
	}

	public static Date getNextDate(Date date) {
		return getNextDate(date, 1);
	}

	public static Date getNextDate(Date date, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, i);
		return calendar.getTime();
	}

	public static Date getNextMonth(Date date) {
		int year = getYearOfDate(date);
		int month = getMonthOfDate(date);
		if (month == 12) {
			year += 1;
			month = 1;
		} else {
			month += 1;
		}
		return DateTimeUtils.getDate(year, month, 1);

	}

	public static Date getNextYear(Date date) {
		int year = getYearOfDate(date);
		return DateTimeUtils.getDate(year + 1, 1, 1);

	}

	public static Date getTodayOfNextYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}

	public static Date[] getMonthStartAndEndDateByDate(Date date) {
		Date[] startAndEndDateOfMonth = new Date[2];
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		startAndEndDateOfMonth[0] = calendar.getTime();

		int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, numberOfDaysInMonth);
		startAndEndDateOfMonth[1] = calendar.getTime();

		return startAndEndDateOfMonth;
	}

	public static Date[] getYearStartAndEndDateByDate(Date date) {
		Date[] startAndEndDateOfYear = new Date[2];
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		startAndEndDateOfYear[0] = calendar.getTime();

		int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, numberOfDaysInMonth);
		startAndEndDateOfYear[1] = calendar.getTime();

		return startAndEndDateOfYear;
	}

	public static Date getLastDateOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYearOfDate(date), getMonthOfDate(date), 1, 0, 0, 0);
		return getPreviousDay(calendar.getTime());
	}

	public static Date getLastDateOfYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYearOfDate(date) + 1, 0, 1, 0, 0, 0);
		return getPreviousDay(calendar.getTime());
	}

	public static Date[] getWeekStartAndEndDateByDate(Date date) {
		Date[] startAndEndDateOfWeek = new Date[2];
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_WEEK, -calendar.get(Calendar.DAY_OF_WEEK) + 1);
		startAndEndDateOfWeek[0] = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, Calendar.SATURDAY - calendar.get(Calendar.DAY_OF_WEEK));
		startAndEndDateOfWeek[1] = calendar.getTime();

		return startAndEndDateOfWeek;
	}

	public static String getDateString(Date date, Locale locale) {
		String dateStr = "";
		DateFormat monthFormat = new SimpleDateFormat("MMM yyyy", locale);
		Calendar monthDate = Calendar.getInstance(locale);

		monthDate.set(getYearOfDate(date), getMonthOfDate(date) - 1, 1, 0, 0, 0);
		dateStr = monthFormat.format(monthDate.getTime());

		return dateStr;

	}

	public static String getYearDateString(Date date, Locale locale) {
		String dateStr = "";
		DateFormat monthFormat = new SimpleDateFormat("yyyy", locale);
		Calendar monthDate = Calendar.getInstance(locale);

		monthDate.set(getYearOfDate(date), getMonthOfDate(date) - 1, 1, 0, 0, 0);
		dateStr = monthFormat.format(monthDate.getTime());

		return dateStr;

	}

	public static Date getNextWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_WEEK, Calendar.SATURDAY - calendar.get(Calendar.DAY_OF_WEEK) + 1);
		return calendar.getTime();
	}

	public static int getMaxDaysOfMonth(Date date) {

		int daysOfMonth;

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			daysOfMonth = 0;
		}

		return daysOfMonth;
	}

	public static int getIntervalDays(Date startDate, Date endDate) {
		if (startDate.after(endDate)) {
			Date cal = startDate;
			startDate = endDate;
			endDate = cal;
		}
		long sl = startDate.getTime();
		long el = endDate.getTime();

		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	public static int getIntervalMonths(Date startDate, Date endDate) {
		if (startDate.after(endDate)) {
			Date cal = startDate;
			startDate = endDate;
			endDate = cal;
		}

		Calendar cStart = Calendar.getInstance();
		cStart.setTime(startDate);
		int startYear = Integer.valueOf(dateFormatYYYY.format(cStart.getTime()));
		int startMonth = Integer.valueOf(dateFormatM.format(cStart.getTime()));

		Calendar cEnd = Calendar.getInstance();
		cEnd.setTime(endDate);
		int endYear = Integer.valueOf(dateFormatYYYY.format(cEnd.getTime()));
		int endMonth = Integer.valueOf(dateFormatM.format(cEnd.getTime()));

		return 12 * (endYear - startYear) + endMonth - startMonth;
	}

	public static Date getRandomDateBetweenTwoDate(Date startDate, Date endDate, boolean randomTime) {
		int intervalDays = getIntervalDays(startDate, endDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.DATE, -1 * Utils.RandomValueWithinRange(1, intervalDays));

		if (randomTime) {
			calendar.setTime(getRandomTime(calendar.getTime()));
		}

		return calendar.getTime();
	}

	public static Date getRandomTime(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		Random r = new Random();
		int hour = Math.abs(r.nextInt() % 24);
		int min = Math.abs(r.nextInt() % 60);
		int sec = Math.abs(r.nextInt() % 60);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);

		return calendar.getTime();
	}

}
