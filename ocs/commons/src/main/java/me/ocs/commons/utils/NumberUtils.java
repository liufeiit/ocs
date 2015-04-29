package me.ocs.commons.utils;

import java.text.DecimalFormat;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月29日 下午6:17:19
 */
public class NumberUtils {

	/**
	 * 格式化数字
	 * 
	 * @param pattern
	 * @param num
	 * @return
	 */
	public static String formatNumber(String pattern, Object num) {
		DecimalFormat formater = new DecimalFormat(pattern);
		return formater.format(num);
	}

	/**
	 * 格式化数字，前补0至digits长度
	 * 
	 * @param digits
	 * @param num
	 * @return
	 */
	public static String formatNumber(int digits, Number num) {
		StringBuilder pattern = new StringBuilder();
		for (int i = 0; i < digits; i++) {
			pattern.append("0");
		}

		return formatNumber(pattern.toString(), num);
	}
}