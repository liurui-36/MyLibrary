package com.erge.mylibrary.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密相关工具类
 */
public class PasswordUtils {
	/**
	 * 这是MD5加密的方法
	 *
	 */
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8")); // 安卓中自带的加密
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString(); // 16进制转换
	}

	/**
	 * 这是SHA1加密的方法
	 * 
	 */
	public static String sha1(String str) {
		if (str == null) {
			return null;
		} else {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
				messageDigest.update(str.getBytes());
				return getFormattedText(messageDigest.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	private static String getFormattedText(byte[] bytes) {
		String des = "";
		String tmp;
		for (int i = 0; i < bytes.length; i++) {
			tmp = (Integer.toHexString(bytes[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}
