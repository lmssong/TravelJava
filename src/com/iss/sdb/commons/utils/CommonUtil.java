package com.iss.sdb.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 共通工具类. <br>
 * 项目中常见逻辑与功能的实现.
 * <p>
 * Copyright: Copyright (c) 2013-11-12 上午11:48:05
 * <p>
 * Company: 
 * <p>
 * @author hqsunc
 * @version 1.0.0
 */
public class CommonUtil {
	/**
	 * 方法描述:判断一个字符串是否为null或空字符串（被trim后为空字符串的也算）。
	 * @param str 需要判断的字符串
	 * @return false：不是空字符串，true：是空字符串
	 * date:2013-11-12
	 * add by: hqsunc
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 方法描述:将null转换成空字符串
	 * @param str-原始字符串
	 * @return String-转换后的字符串
	 * date:2013-11-13
	 * add by: hqsunc
	 */
	public static String changeNullToEmpty(String str){
		if(isEmpty(str)){
			return "";
		}else{
			return str;
		}
	}
	/**
	 * 方法描述:null转为"" 非null的对象转化为String
	 * @param 任何一类对象
	 * @return obj.toString
	 * date:Nov 26, 2013
	 * add by: hqsunc
	 */
	public static String changeNullToEmptyString(Object obj){
		if(obj == null)
			return "";
		else
			return obj.toString();
	}
	
	
	/**
	 * 
	 * 方法描述:对一个字符串进行MD5加密
	 * @param sourceStr - 原始字符串
	 * @return String - 加密后的长度为32的字符串（如果原始字符串为null或"",该方法返回null）
	 * date:2013-11-13
	 * add by: hqsunc
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getMD5(String sourceStr) throws NoSuchAlgorithmException {
		if(sourceStr==null || "".equals(sourceStr.trim())){
			return null;
		}
		byte[] source=sourceStr.getBytes();
		String s = null;
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};
		try{
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update(source);
		    byte tmp[] = md.digest();					// MD5 的计算结果是一个 128 位的长整数， 用字节表示就是 16 个字节
		    char str[] = new char[16 * 2];				// 每个字节用 16 进制表示的话，使用两个字符， 所以表示成 16 进制需要 32 个字符
		    int k = 0;                                	// 表示转换结果中对应的字符位置
		    for (int i = 0; i < 16; i++) {          	// 从第一个字节开始，对 MD5 的每一个字节 ,转换成 16 进制字符的转换
		    	byte byte0 = tmp[i];                 	// 取第 i 个字节
		    	str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4 位的数字转换,  >>> 为逻辑右移，将符号位一起右移
		    	str[k++] = hexDigits[byte0 & 0xf];      // 取字节中低 4 位的数字转换
		    }
		    s = new String(str);						// 换后的结果转换为字符串
		   }catch(NoSuchAlgorithmException e){
			   throw e;
		   }
		   return s;
	}
		
	/**
	 * 方法描述:获取格式化后的异常信息
	 * @param stackTraceElement 用来获取类名与方法名的栈信息
	 * @param e 异常对象
	 * @return 格式化后的异常信息
	 * date:2014-2-20
	 * add by: hqsunc
	 */
	public static String getErrorMessage(StackTraceElement stackTraceElement,Exception e) {
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append(e.toString() + "\n");
		StackTraceElement[] stacks = e.getStackTrace();
		if(stacks != null) {
			for(StackTraceElement stack : stacks) {
				errorMessage.append("\t" + stack.toString() + "\n");
			}
		}
		return getErrorMessage(stackTraceElement, errorMessage.toString());
	}
	
	/**
	 * 方法描述:获取格式化后的异常信息
	 * @param stackTraceElement 用来获取类名与方法名的栈信息
	 * @param errorMessage 格式化前的异常信息
	 * @return 格式化后的异常信息
	 * date:2014-4-8
	 * add by: hqsunc
	 */
	public static String getErrorMessage(StackTraceElement stackTraceElement,String errorMessage) {
		StringBuilder result = new StringBuilder();
		result.append("[" + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "]:failed. throw e:" + errorMessage);
		return result.toString();
	}
}
