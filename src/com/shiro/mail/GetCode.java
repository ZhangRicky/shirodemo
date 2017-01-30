package com.shiro.mail;

import java.io.UnsupportedEncodingException;

/**
 * 获取文本的编码格式
 * @author Mark
 *
 */
public class GetCode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String sss="é¢çº¦æ³¨åå¤±è´¥ï¼[å§å:é¾ä¿;è¯ä»¶å·:522222199301012034;å°±è¯åºå·:]å¨è¿å¤©[2016-12-16 23:59:00]æç­ID[173]å·²ç»é¢çº¦æäºï¼ä¸åè®¸éå¤é¢çº¦æåä¸ä¸ªå·å«!--æ¥è¯¢æä¸ªæ¥ææ¯å¦å·²ç»é¢çº¦æäºæä¸ªå·å« ";
		String s=getCode("é¢çº¦æ³¨åå¤±è´¥ï¼[å§å:é¾ä¿;è¯ä»¶å·:522222199301012034;å°±è¯åºå·:]å¨è¿å¤©[2016-12-16 23:59:00]æç­ID[173]å·²ç»é¢çº¦æäºï¼ä¸åè®¸éå¤é¢çº¦æåä¸ä¸ªå·å«!--æ¥è¯¢æä¸ªæ¥ææ¯å¦å·²ç»é¢çº¦æäºæä¸ªå·å« ");
		byte[] b =sss.getBytes("ISO-8859-1");
		String str = new String(b,"UTF-8");
		System.out.println(str);
		System.out.println(s);
	}
	public static String getCode(String str){
		
	
		String encode = "GB2312";
	    try {
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	            String s = encode;
	            return s;
	        }
	    } catch (Exception exception) {
	    }
	    encode = "ISO-8859-1";
	    try {
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	            String s1 = encode;
	            return s1;
	        }
	    } catch (Exception exception1) {
	    }
	    encode = "UTF-8";
	    try {
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	            String s2 = encode;
	            return s2;
	        }
	    } catch (Exception exception2) {
	    }
	    encode = "GBK";
	    try {
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	            String s3 = encode;
	            return s3;
	        }
	    } catch (Exception exception3) {
	    }
	    return "";
	}
}
