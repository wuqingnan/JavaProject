package com.shizy.demo.pinyin;

import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	/**
	 * 将中文转换为拼音
	 * @param string
	 * @return
	 */
	public static String convertToPinYin(String string) {
		if (string == null || string.equals("")) {
			return " ";
		}
		StringBuffer buffer = new StringBuffer();
		char[] charArray = string.toCharArray();
		// 根据需要定制输出格式，我用默认的即可
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] pinyin = null;

		// 遍历数组，ASC码大于128进行转换
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] > 128) {
				try {
					pinyin = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
					if (pinyin != null) {
						buffer.append(pinyin[0] + " ");
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					buffer.append(" ");
				}
			} else {
				buffer.append(charArray[i]);
			}
		}
		return buffer.toString().trim().replaceAll("\\s+", " ").toLowerCase();
	}
	
	/**
	 * 获得汉语拼音首字母
	 * @param str
	 * @return
	 */
	public static String getAlpha(String str) {
		if (str == null || str.trim().length() == 0) {
			return "#";
		}

		String c = str.trim().substring(0, 1);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+");
		if (pattern.matcher(c).matches()) {
			return c.toUpperCase();
		} else {
			return "#";
		}
	}
	
	/**
	 * 判断filter是否匹配拼音
	 * @param filter
	 * @param pinyin
	 * @return
	 */
	public static boolean matcher(String filter, String pinyin) {
		String[] strs = pinyin.split(" ");
		int index = -1;
		for(int i = 0; i < strs.length; i++) {
			if (filter.charAt(0) == strs[i].charAt(0)) {
				index = i;
				break;
			}
		}
		
		if (index < 0) {
			return false;
		}
		else if (filter.length() == 1) {
			return true;
		}
		else {
			return matcher(filter, strs, index);
		}
	}
	
	/**
	 * 判断filter是否匹配拼音
	 * @param filter
	 * @param pinyin
	 * @param index
	 * @return
	 */
	private static boolean matcher(String filter, String[] pinyin, int index){
		if (filter.charAt(0) != pinyin[index].charAt(0)) {
			return false;
		}
		else if (pinyin[index].startsWith(filter)) {
			return true;
		}
		else if (filter.startsWith(pinyin[index])) {
			if (filter.length() == pinyin[index].length()) {
				return true;
			}
			int len = pinyin[index].length();
			index ++;
			if (index >= pinyin.length) {
				return false;
			}
			return matcher(filter.substring(len), pinyin, index);
		}
		else {
			index ++;
			if (index >= pinyin.length) {
				return false;
			}
			return matcher(filter.substring(1), pinyin, index);
		}
	}
}
