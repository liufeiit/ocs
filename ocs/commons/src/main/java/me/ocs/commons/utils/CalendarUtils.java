package me.ocs.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0
 * @since 2014年8月29日 下午1:40:25
 */
public class CalendarUtils {
	
	public static final String S_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String S_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy_MM_dd_HH_mm_ss";
	public static final String Y_M_D_Z = "yyyy-MM-dd'T'HH:mm:ssZZZ";

	public static final String DATE_YM_FORMAT = "yyyy_MM";

	public static final String DATE_YM_DASH_FORMAT = "yyyy-MM";

	public static String formatDate(Date date, String pattern) {
		if (date == null)
			throw new IllegalArgumentException("date is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static Date parseDate(String date, String pattern) throws ParseException {
		if (date == null)
			throw new IllegalArgumentException("date is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(date);
	}
}