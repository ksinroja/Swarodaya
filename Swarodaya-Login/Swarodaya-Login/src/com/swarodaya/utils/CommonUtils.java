package com.swarodaya.utils;




public class CommonUtils {
	
	public static String createSuccessJson(boolean isSuccess){
		return createSuccessJson(isSuccess, null);
	}
	public static String createSuccessJson(boolean isSuccess, String message){
		StringBuffer jsonStr = new StringBuffer();
		
		jsonStr.append("{\"success\":\""+Boolean.toString(isSuccess)+"\",\"message\":\""+message+"\"}");
		return jsonStr.toString();
	}
	public static String extractFinancialYear(String jobCode){
		String fYear = "";
		int pos = org.apache.commons.lang.StringUtils.ordinalIndexOf(jobCode, "-", 3);
		fYear = jobCode.substring(pos+1, jobCode.length());
		return fYear;
	}
}
