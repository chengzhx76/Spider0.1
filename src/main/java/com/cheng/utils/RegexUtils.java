package com.cheng.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static String getFirstDir(String imgPath) {
		Pattern p = Pattern.compile("\\/\\d{4}\\/");
	  	Matcher m = p.matcher(imgPath);
		if(m.find()) {
			return m.group().replace("/", "");
		}else {
			return null;
		}
	}
	public static String getSecondDir(String imgPath) {
		Pattern p = Pattern.compile("\\/\\d{2}\\/");
		Matcher m = p.matcher(imgPath);
		if(m.find()) {
			return m.group().replace("/", "");
		}else {
			return null;
		}
	}
}
