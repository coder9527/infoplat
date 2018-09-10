package com.info.baseService.util;




import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtil {
	/**
	 * author :lijin;
	 * discribe :this class is base java.util
	 * to simplify the develop which contain String,formate and other
	 * &&
	 * date :2014-05;
	 **/
	
	/**
	 *get the number of the String 
	 */
	public static String getNumFromStr(String str){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(str);   
		return m.replaceAll("").trim();
	}
	
	/**
	 *set the number String like the format of '2.22' 
	 */
	public static String getAfterPot2(String str){
		  java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");  
		  return df.format(Double.parseDouble(str));
	}
	
	/**
	 *set the number Double like the format of '2.22' 
	 */
	public static String getAfterPot2(Double num){
		  java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");  
		  return df.format(num);
	}
	
	/**
	 * charecter deal method��transfer first charecter to upcase 
	 * string aaa
	 * return  Aaa
	 */
	public static String firstToUpperCase(String string) {
		String post = string.substring(1, string.length());
		String first = ("" + string.charAt(0)).toUpperCase();
		return first + post;
	}
	
}
