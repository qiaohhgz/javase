package com.jim.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA. User: bais01 Date: Jun 7, 2009 Time: 12:43:53 AM To
 * change this template use File | Settings | File Templates.
 */
public class DateTimeUtils {
    /**
     *
     * @param date
     * @return
     */
	public static Date removeTime(final Date date) {
		if (date == null)   {
            return null;
        }

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date Today() {
		Calendar calendar = Calendar.getInstance();
		Date today = removeTime(calendar.getTime());
		return today;
	}

	public static Date Now() {
		return Calendar.getInstance().getTime();
	}

	public static Date Yesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date yesterday = removeTime(cal.getTime());
		return yesterday;
	}

	public static Date addTime(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        field = 10;
		cal.add(field, amount);
		return cal.getTime();
	}

	// iMonth is 1 to 12 rather than 0 to 11 in java
	public static Date getDate(int iYear, int iMonth, int iDate) {
		if (iYear < 1 || iMonth < 1 || iDate < 1 || iMonth > 12 || iDate > 31)
			return null;

		Calendar cal = Calendar.getInstance();
		cal.set(iYear, iMonth - 1, iDate);
		return removeTime(cal.getTime());
	}

	public static int getTimeZoneOffset(String timezone) {
		Calendar cd = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		TimeZone timeZoneCST = TimeZone.getTimeZone(timezone);
		timeZoneCST.getOffset(cd.getTimeInMillis());
		return timeZoneCST.getOffset(cd.getTimeInMillis()) / (1000 * 3600);
	}

	public static boolean isInDurantion(Calendar targetDate, Calendar startDate, Calendar endDate) {
		boolean isTrue = false;
		if (compareCalendar(targetDate, startDate) >= 0 && compareCalendar(targetDate, endDate) < 0) {
			isTrue = true;
		}
		return isTrue;
	}

	public static int compareCalendar(Calendar cmprd, Calendar cmpto) {
		int flag = -1;
		long cmprdSec = cmprd.getTimeInMillis() - cmprd.getTimeZone().getRawOffset();
		long cmptoSec = cmpto.getTimeInMillis() - cmpto.getTimeZone().getRawOffset();
		if (cmprdSec > cmptoSec)
			flag = 1;
		else if (cmprdSec == cmptoSec)
			flag = 0;

		return flag;
	}

}
